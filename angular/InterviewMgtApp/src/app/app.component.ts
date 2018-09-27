import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {


  constructor(private http: HttpClient) {

  }

  ngOnInit() {
    this.http.get('http://localhost:8082/api/hello')
    .subscribe(data => console.log(data),
    err => console.log(err));
  }
}
