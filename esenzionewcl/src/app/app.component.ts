import { Component, OnInit } from '@angular/core';
import { HttpModule, Http } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/switchMap';
import { Observable } from "rxjs/Observable";
import { AppOperatoreComponent } from './appoperatore/appoperatore.component';
import { AppOperatorepatoComponent } from './appoperatorepato/appoperatorepato.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule, ActivatedRoute, Router, Params } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { NgIf } from '@angular/common';
import { AssistitoStoreService } from './assistito/assistito.service'
import { Location } from '@angular/common';
import { UserInfo } from './assistito/userinfo';
import { AfterViewInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl } from '@angular/forms';//
import { Assistito } from "app/assistito/assistito";
import { ConfigService } from "app/services/config.service";
//import { environment } from "../environments/environment";
import * as $ from 'jquery';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@Component( {
    selector: 'app-root',
    templateUrl: 'app.component.html',
    // styleUrls: ['./app.component.css'],

} )

export class AppComponent implements OnInit {
//, AfterViewInit  {
    user: UserInfo;
codFisc: string;
mainForm: FormGroup = null;
myData: Array<any>;
message = null;
portal = null;
statusCode: number;
 public loading = false;
public codfisctrovato: boolean = false;

    constructor(private assistitoservice: AssistitoStoreService,
            private route: ActivatedRoute,
            private router: Router,
            private fb: FormBuilder
            ,private config: ConfigService) {
    }
    ngOnInit() {
        this.mainForm = this.fb.group( {} );
        this.portal = this.getQueryVariable( "portal" );
        if ( this.portal == "sisp" ) {
            document.getElementById( "menuservizi" ).style.display = "";
        }


        document.getElementById( "prenotazioni" ).setAttribute( "href", this.config.getPrenotazioni() );
        document.getElementById( "ticket" ).setAttribute( "href", this.config.getTicket() );
        document.getElementById( "salute" ).setAttribute( "href", this.config.getSalute() );
        document.getElementById( "salute1" ).setAttribute( "href", this.config.getSalute() );
        document.getElementById( "referti" ).setAttribute( "href", this.config.getReferti() );
        document.getElementById( "referti1" ).setAttribute( "href", this.config.getReferti() );
        document.getElementById( "fascicolos" ).setAttribute( "href", this.config.getFascicolos() );
        document.getElementById( "fascicolof" ).setAttribute( "href", this.config.getFascicolof() );
        document.getElementById( "screening" ).setAttribute( "href", this.config.getScreening() );
        document.getElementById( "cambiomedico" ).setAttribute( "href", this.config.getCambiomedico() );
        document.getElementById( "deleghe" ).setAttribute( "href", this.config.getDeleghe() );
        document.getElementById( "deleghe1" ).setAttribute( "href", this.config.getDeleghe() );
        if ( this.portal == "sisp" ) {
            document.getElementById( "mailassistenza" ).innerHTML = this.config.getMailAssistenza();
        }
        else {
            document.getElementById( "mailassistenza" ).innerHTML = this.config.getMailAssistenzaPa();
        }
    }



    ApriHome(){
        this.loading = true;
        this.getUtenteEsenpat();
    }

    ApriEsenpat(){
        this.loading = true;
        this.getUtenteEsenpat();
    }

    ApriEsenred(){
        this.loading = true;
        this.getUtenteEsenred();

    }

    getUtenteEsenred() {
        document.getElementById("logError").hidden = true;
        this.assistitoservice.getLoginEsenred()
            .subscribe( successCode => {
                let body = successCode.json();
                if ( body.code == 200 ) {
                    this.user = body.data;
                    this.loading = false;
                    $( "#home" ).hide();
                    this.router.navigate( ["/appoperatore/" + this.user.codFisc], { skipLocationChange: true } );
                } else {
                    document.getElementById("logError").hidden = false;
                    ( document.getElementById( "messaggiononaccesso" ).innerHTML ) = body.data;
                    document.getElementById( "utnonaut" ).style.visibility = 'visible';
                    this.loading = false;
                }
            },
            //gestione messaggi
            errorCode => {
                let body = errorCode.json();
                document.getElementById("logError").hidden = false;
                ( document.getElementById( "messaggiononaccesso" ).innerHTML ) = body.data;
                document.getElementById( "utnonaut" ).style.visibility = 'visible';
                this.loading = false;
            }
            );
    }

    getUtenteEsenpat() {
        document.getElementById("logError").hidden = true;
        this.assistitoservice.getLoginEsenpat()
            .subscribe( successCode => {
                let body = successCode.json();
                if ( body.code == 200 ) {
                    this.user = body.data;
                    this.loading = false;
                    this.codfisctrovato = true;
                    this.assistitoservice.setUser(this.user);
                    $( "#home" ).hide();
                    this.router.navigate( ["/appoperatorepato/" + this.user.codFisc], { skipLocationChange: true } );
                } else {
                    document.getElementById("logError").hidden = false;
                    ( document.getElementById( "messaggiononaccesso" ).innerHTML ) = body.data;
                    document.getElementById( "utnonaut" ).style.visibility = 'visible';
                    this.codfisctrovato = false;
                    this.loading = false;
                }
            },
            //gestione messaggi
            errorCode => {
                let body = errorCode.json();
                document.getElementById("logError").hidden = false;
                ( document.getElementById( "messaggiononaccesso" ).innerHTML ) = body.data;
                document.getElementById( "utnonaut" ).style.visibility = 'visible';
                this.loading = false;
            }
            );
    }

    getQueryVariable( variable ) {
        var url = window.location.href;
        return url.substring( url.lastIndexOf( "=" ) + 1, url.length );
    }

}
