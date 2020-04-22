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
    assistIn:string;timeIn:any;
    showAddGoal=false;
    showAddGoalText="+1";
    zostava;
    allGoals;
    id;
    striedanie;
    constructor(private route: ActivatedRoute, private api: ApiService, private auth: AuthService) {
    }
    ngOnInit() {
        this.route.paramMap.subscribe(params => {
            this.id = params.get('matchID');
            this.api.getDetailMatch(this.id).subscribe(data => {
                var r = data['Rozhodci'][0];
                this.rozhodca = r['jmeno'];
                this.datum = (data['datum']);
                this.divaci = data['pocet_divaku'];
                this.stadion = data['stadion'];
                var hoste = data['hoste'];
                var domaci = data['domaci'];
                var goly = data['Goly'];
                this.allGoals=goly;
                this.striedanie = data['Stridani'];
                var s=data['Stridani']
                this.hostiaFlag = flags[hoste['id_team'] - 1];
                this.domaciFlag = flags[domaci['id_team'] - 1];

                var domaciSquad = domaci['sestava'];
                
                var hosteSquad = hoste['sestava'];
                this.zostava=domaciSquad.concat(hosteSquad);
                domaciSquad.forEach(function (element, i) {
                    if (i < 11)
                        element.poz = "11";
                    else
                        element.poz = "N";
                    element.goals=0;
                    element.goalsTime=[];
                    element.ass=[];
                    goly.forEach(element2 => {
                        if (element['name'].includes(element2['hrac1'])){
                            element.goals++;
                            element.goalsTime.push(element2.cas);
                            element.ass.push(element2.hrac2);
                            console.log(element['name'],element2['hrac1'],element.goals)
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
                this.domaciTim = domaci['tym'];
                this.hostiaTim = hoste['tym'];
                this.score = data['domaci_goly'] + ":" + data['hoste_goly']


            })
        })

    }
    arrayOne(n: string): any[] {
        var arr = new Array(+n);

        return arr;
    }
    addGoal(n,a:string) {
        
        if (this.showAddGoalText=="+1"){ 
            this.showAddGoalText="OK";
            this.showAddGoal=true;
        }
        else{
            this.showAddGoalText="OK";
            n.goalsTime.push(a);
            n.ass.push(n.assistIn);
            this.showAddGoalText="+1";
            this.showAddGoal=false;
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
    sendPost(){

    }
    showTime(n,id){
        return "minuta:"+n.goalsTime[id]+"| assist:"+n.ass[id];
    }
    selected:string;
    goal={
        id:0,
        gol_cas:0,
        gol_typ:"",
        polovina_zapasu:"",
        zapa:{id:0},
        hrac1:{id:0},
        hrac2:{id:0}
        
    };

    deletegoal;
    newGoal(e){
        this.goal.zapa.id=+this.id;
        
        //console.log(this.goal);
        this.auth.sendGoal(this.goal.gol_cas,this.goal.gol_typ,this.goal.polovina_zapasu,this.goal.zapa,this.goal.hrac1,this.goal.hrac2).subscribe(
            data => {
                console.log(data);
            });
            
    }
    deleteGoal(e){
        this.auth.deleteGoal(this.deletegoal).subscribe(data=>
            console.log(data));
    }
    striedani={
        id:0,
        cas:0,
        hrac_id_in:"",
        hrac_id_out:"",
        zapa:{id:0}

    };
    newStriedanie(e){
        this.striedani.zapa.id=+this.id;
        console.log(this.striedanie);
        this.auth.sendSub(this.striedani.id,+this.striedani.cas,this.striedani.hrac_id_in,this.striedani.hrac_id_out,this.striedani.zapa).subscribe(data=>{
            console.log(data);
        });
    }
    delSub;
    DeleteSub(e){
        this.auth.deleteSub(this.delSub).subscribe(data=>
            console.log(data));
    }
}