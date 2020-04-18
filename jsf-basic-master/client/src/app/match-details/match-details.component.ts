import { Component, OnInit } from '@angular/core';
import { mmatches } from '../matches';
import { ActivatedRoute } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';
import { players } from '../players';
import { ApiService } from '../api.service';
import { flags } from '../img';

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

    constructor(private route: ActivatedRoute, private api: ApiService) {
    }
    ngOnInit() {


        this.route.paramMap.subscribe(params => {
            var id = params.get('matchID');
            this.api.getDetailMatch(id).subscribe(data => {
                var r= data['Rozhodci'][0];
                this.rozhodca = r['jmeno'];
                this.datum = (data['datum']);
                this.divaci = data['pocet_divaku'];
                this.stadion = data['stadion'];
                var hoste = data['hoste'];
                var domaci = data['domaci'];
                var goly = data['Goly'];
                var striedanie = data['Stridani'];
                this.hostiaFlag = flags[hoste['id_team'] - 1];
                this.domaciFlag = flags[domaci['id_team'] - 1];

                var domaciSquad = domaci['sestava'];
                var hosteSquad = hoste['sestava'];
                domaciSquad.forEach(function (element, i) {
                    if (i < 11)
                        element.poz = "11";
                    else
                        element.poz = "N";
                    element.goals = 0;

                    goly.forEach(element2 => {
                        if (element['name'].includes(element2['hrac1']))
                            element.goals++;
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
                    striedanie.forEach(element3 => {
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
}