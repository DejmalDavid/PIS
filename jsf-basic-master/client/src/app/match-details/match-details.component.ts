import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { players } from '../players';
import { ApiService } from '../api.service';
import { flags } from '../img';
import { AuthService } from '../auth.service';


@Component({
    selector: 'app-match-details',
    templateUrl: './match-details.component.html',
    styleUrls: ['./match-details.component.css']
})

export class MatchDetailsComponent implements OnInit {

    score: string;
    displayedColumnsSquadHome: string[] = ['numberHome', 'playerHome'];
    displayedColumnsSquadAway: string[] = ['playerAway', 'numberAway'];

    dataSourceSquadHome = new MatTableDataSource();
    dataSourceSquadAway = new MatTableDataSource(players);
    goalsByPlayer: Int8Array;
    domaciTim: string;
    hostiaTim: string;
    hostiaFlag: string;
    domaciFlag: string;
    stadion: string; divaci: string; datum: string; rozhodca: string

    isAdmin = this.auth.getIsSuperAdmin;
    assistIn: string; timeIn: any;
    showAddGoal = false;
    showAddGoalText = "+1";
    zostava;
    allGoals;
    id;
    striedanie;
    homeIsEmpty;
    awayIsEmpty;
    rozhod;
    rozhodci={jmeno:""};
    domaciSkore;
    hostiaSkore;
    skupina;
    constructor(private route: ActivatedRoute, private api: ApiService, private auth: AuthService) {
    }
    ngOnInit() {
        this.route.paramMap.subscribe(params => {
            this.id = params.get('matchID');
            this.api.getDetailMatch(this.id).subscribe(data => {
                this.rozhod = data['Rozhodci'][0];
                if (typeof this.rozhod === 'undefined'){
                    this.rozhodca = "";
                    this.rozhodci.jmeno="";
                }
                else{
                    this.rozhodca = this.rozhod['jmeno'];
                    this.rozhodci.jmeno=this.rozhod.jmeno;
                }
                this.skupina=data['skupina'];
                this.datum = (data['datum']);
                this.divaci = data['pocet_divaku'];
                this.pocet_divakov=this.divaci;
                this.stadion = data['stadion'];
                this.stadion1=this.stadion;
                var hoste = data['hoste'];
                var domaci = data['domaci'];
                var goly = data['Goly'];
                this.allGoals = goly;
                this.striedanie = data['Stridani'];
                var s = data['Stridani'];
                var hosteSquad;
                if (typeof hoste === 'undefined') {
                    this.hostiaFlag = "";
                    hosteSquad = [];
                    this.hostiaTim="";
                    this.awayIsEmpty=true;
                }
                else {
                    this.hostiaFlag = flags[hoste['id_team'] - 1];
                    hosteSquad = hoste['sestava'];
                    this.hostiaTim = hoste['tym'];
                    this.awayIsEmpty=false;
                }
                var domaciSquad;
                if (typeof domaci === 'undefined') {
                    this.domaciFlag = "";
                    domaciSquad =[];
                    this.domaciTim="";
                    this.homeIsEmpty=true;
                }
                else {
                    this.domaciFlag = flags[domaci['id_team'] - 1];
                    domaciSquad = domaci['sestava'];
                    this.domaciTim = domaci['tym'];
                    this.homeIsEmpty=false;
                    
                }



                this.zostava = domaciSquad.concat(hosteSquad);
                domaciSquad.forEach(function (element, i) {
                    if (i < 11)
                        element.poz = "11";
                    else
                        element.poz = "N";
                    element.goals = 0;
                    element.goalsTime = [];
                    element.ass = [];
                    goly.forEach(element2 => {
                        if (element['name'].includes(element2['hrac1'])) {
                            element.goals++;
                            element.goalsTime.push(element2.cas);
                            element.ass.push(element2.hrac2);
                            console.log(element['name'], element2['hrac1'], element.goals)
                        }
                    });
                });
                hosteSquad.forEach(function (element, i) {
                    if (i < 11)
                        element.poz = "11";
                    else
                        element.poz = "N";
                    element.goals = 0;

                    goly.forEach(element2 => {
                        if (element['name'].includes(element2['hrac1']))
                            element.goals++;
                    });
                    s.forEach(element3 => {
                        if (element['id'] == element3['id_out']) {
                            element.out = element3['id_in'];
                        }
                        if (element['id'] == element3['id_in']) {
                            element.in = element3['id_out'];

                        }
                    });
                });
                hosteSquad.forEach(element => {
                    if (element.hasOwnProperty('in')) {
                        hosteSquad.forEach(element2 => {
                            if (element['in'] == element2['id'])
                                element.in = element2.name;
                        });
                    }
                    if (element.hasOwnProperty('out')) {
                        hosteSquad.forEach(element2 => {
                            if (element['out'] == element2['id'])
                                element.out = element2.name;
                        });
                    }

                });

                console.log(hosteSquad);


                this.dataSourceSquadHome = domaciSquad;
                this.dataSourceSquadAway = hosteSquad;
                
                this.score = data['domaci_goly'] + ":" + data['hoste_goly']
                this.domaciSkore=data['domaci_goly'];
                this.hostiaSkore=data['hoste_goly']

            })
        })

    }
    arrayOne(n: string): any[] {
        var arr = new Array(+n);

        return arr;
    }
    addGoal(n, a: string) {

        if (this.showAddGoalText == "+1") {
            this.showAddGoalText = "OK";
            this.showAddGoal = true;
        }
        else {
            this.showAddGoalText = "OK";
            n.goalsTime.push(a);
            n.ass.push(n.assistIn);
            this.showAddGoalText = "+1";
            this.showAddGoal = false;
            n.goals++;
            console.log(this.timeIn);
        }

    }
    loginUser(event) {
        event.preventDefault()
        const target = event.target
        const username = target.querySelector('#username').value
        console.log(username);
    }
    removeGoal(n) {
        if (n.goals > 0)
            n.goals--;
    }
    stadion1;pocet_divakov=this.divaci;
    updateMatchInfo() {
        this.route.paramMap.subscribe(params => {
            var i = params.get('matchID');
            this.auth.updateMatch(this.stadion1,this.pocet_divakov,this.rozhodci.jmeno,+i).subscribe(data=>{
                console.log(this.stadion1,this.pocet_divakov,this.rozhodci.jmeno,+i)
                console.log("aaa"+data);
            })
        });
        
    }
    showTime(n, id) {
        return "minuta:" + n.goalsTime[id] + "| assist:" + n.ass[id];
    }
    selected: string;
    goal = {
        id: 0,
        gol_cas: 0,
        gol_typ: "",
        polovina_zapasu: "",
        zapa: { id: 0 },
        hrac1: { id: 0 },
        hrac2: { id: 0 }

    };

    deletegoal;
    newGoal(e) {
        this.goal.zapa.id = +this.id;

        //console.log(this.goal);
        this.auth.sendGoal(this.goal.gol_cas, this.goal.gol_typ, this.goal.polovina_zapasu, this.goal.zapa, this.goal.hrac1, this.goal.hrac2).subscribe(
            data => {
                console.log(data);
            });

    }
    deleteGoal(e) {
        this.auth.deleteGoal(this.deletegoal).subscribe(data =>
            console.log(data));
    }
    striedani = {
        id: 0,
        cas: 0,
        hrac_id_in: "",
        hrac_id_out: "",
        zapa: { id: 0 }

    };
    newStriedanie(e) {
        this.striedani.zapa.id = +this.id;
        console.log(this.striedanie);
        this.auth.sendSub(this.striedani.id, +this.striedani.cas, this.striedani.hrac_id_in, this.striedani.hrac_id_out, this.striedani.zapa).subscribe(data => {
            console.log(data);
        });
    }
    delSub;
    DeleteSub(e) {
        this.auth.deleteSub(this.delSub).subscribe(data =>
            console.log(data));
    }
}