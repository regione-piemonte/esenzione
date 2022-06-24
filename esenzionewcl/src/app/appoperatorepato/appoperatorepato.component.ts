import { Component, Input, OnInit, Output, Directive, ElementRef, ViewChild } from '@angular/core';
import { HttpModule, Http } from '@angular/http';
import { FormsModule } from '@angular/forms';
import { parametroStoreService } from '../parametro/parametro.service';
import { parametro } from '../parametro/parametro';
import { FormControl, FormGroup, Validators, AbstractControl, FormBuilder } from '@angular/forms';
import { Router, Params, RouterModule, ActivatedRoute } from '@angular/router';
import { NgIf, NgSwitch } from '@angular/common';
import { CookieService } from 'ngx-cookie-service';//questo
import { EventEmitter } from "events";
import * as $ from 'jquery';
import { ConfigService } from "../services/config.service";
import { ricercaAssistito } from './ricercaAssistito/ricercaAssistito.component';
import { statodocumento } from "app/statodocumento/statodocumento";
import { CodiceEsenzionePatologia } from "app/esenzione/codiceesenzionepatologia";
import { statodocumentoStoreService } from "app/statodocumento/statodocumento.service";
import { EsenzioneStoreService } from "app/esenzione/esenzione.service";
import { AssistitoStoreService } from "app/assistito/assistito.service";
import { UserInfo } from "app/assistito/userinfo";
import { WorkflowService } from 'app/certificato-wizard/workflow/workflow.service';
import { EsenWorkflowService } from 'app/esenzione-wizard/workflow/workflow.service';
import { FormDataService } from 'app/certificato-wizard/data/formData.service';
import { EsenFormDataService } from 'app/esenzione-wizard/data/esenFormData.service';
declare var $;
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@Component( {
    selector: 'appoperatorepato',
    templateUrl: './appoperatorepato.component.html',


} )


export class AppOperatorepatoComponent implements OnInit {

    listaAssistiti: UserInfo[] = [];
    user: UserInfo;

    constructor(
            private router: Router,
            private route: ActivatedRoute,
            private assistitoStoreService: AssistitoStoreService,
            private config: ConfigService,
            private workflowService: WorkflowService,
            private esenWorkflowService: EsenWorkflowService,
            private formDataService: FormDataService,
            private esenFormDataService: EsenFormDataService,
            private cookieService: CookieService
        ) {
        }

    ngOnInit(){
        this.user = this.assistitoStoreService.getUser();
        this.listaAssistiti[0] = this.user;
    }


    goToHome(){
        $( "#home" ).hide();
        this.workflowService.resetWorkFlow();
        this.esenWorkflowService.setFromCert(false);
        this.formDataService.resetFormData();
        this.esenFormDataService.resetFormData();
        this.router.navigate( ["/appoperatorepato/" + this.user.codFisc], { skipLocationChange: true } );
    }

    escilogout() {
        this.assistitoStoreService.getLogout()
            .subscribe( successCode => {
                let statusCode = successCode.status;
                var str = successCode._body;
                var objmsg = JSON.parse( str );
                if ( statusCode == 204 ) {
                    localStorage.clear();
                    localStorage.removeItem( "cognome" );
                    localStorage.removeItem( "nome" );
                    localStorage.removeItem( "codFisc" );
                    localStorage.removeItem( "idAura" );
                    localStorage.removeItem( "codASL" );
                    sessionStorage.clear();
                    this.cookieService.deleteAll();

                    this.router.navigate(["/"]).then(result=>{window.location.replace(this.config.getOnAppExitURLOperatore())});

                }
            } );
    }

    paginaPersonale() {
        window.open(this.config.getProfilo(), "_blank");
    }

}
