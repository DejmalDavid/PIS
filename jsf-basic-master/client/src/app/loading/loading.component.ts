import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-loading',
  templateUrl: './loading.component.html',
  styleUrls: ['./loading.component.css']
})
export class LoadingComponent implements OnInit {
  url: string;
  constructor(private router: Router, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.subscribe(event => {
      this.url = event.url.replace("%2F", "/");
      this.url = this.url.split("/")[1] + "/" + event.data
    });
    setTimeout(() => {
      this.router.navigate([this.url])
    }, 300)
  }

}
