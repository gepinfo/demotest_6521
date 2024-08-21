import { Component, OnInit, ViewChild } from '@angular/core';
import { Service } from './.service';





@Component({
  selector: 'app-',
  templateUrl: './.component.html',
  styleUrls: ['./.component.scss'],
})

export class Component implements OnInit {
    public empa:any = {
        created_date: '',
        created_by: '',
        last_modified_by: '',
        last_modified_date: '',
        empname: '',
        age: '',
    }




    constructor (
        private Service: Service,
    ) { }

    ngOnInit() {
        this.empa.created_by = sessionStorage.getItem('email') || ''; 
        


    
    }


}