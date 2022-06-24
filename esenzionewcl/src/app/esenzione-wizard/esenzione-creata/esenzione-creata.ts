import { Component, OnInit, Input } from '@angular/core';

import { FormDataService } from "app/certificato-wizard/data/formData.service";
import { WorkflowService } from "app/certificato-wizard/workflow/workflow.service";
import { Router, ActivatedRoute } from "@angular/router";
import { CertErrorHandler } from "app/certificato-wizard/certErrorHandler";
import { DatePipe } from '@angular/common';
import { EsenFormDataService } from "app/esenzione-wizard/data/esenFormData.service";
import { EsenWorkflowService } from "app/esenzione-wizard/workflow/workflow.service";
import { AssistitoStoreService } from 'app/assistito/assistito.service';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@Component({
  selector: 'app-esen-creata',
  templateUrl: './esenzione-creata.component.html',
  styleUrls: ['./esenzione-creata.css']
})
export class EsenzioneCreataComponent implements OnInit {

    numeroPratica: string = '';
    dataPratica: string = '';
    fromCert: boolean = false;

    constructor(private esenFormDataService: EsenFormDataService,
                private workFlowService: EsenWorkflowService,
                private certWorkFlowService: WorkflowService,
                private router: Router,
                private route: ActivatedRoute,
                private errorHandler: CertErrorHandler,
                private assistitoStoreService: AssistitoStoreService) { }


    ngOnInit() {
            this.errorHandler.setHidden(true);
            this.numeroPratica = this.workFlowService.getEsenNumber();
            const format = 'dd/MM/yyyy';
            var datePipe = new DatePipe('en-US');
            let dateNow = datePipe.transform(new Date(), format);
            this.dataPratica = dateNow;
            this.fromCert = this.workFlowService.getFromCert();
    }

    goToHome(){
        this.workFlowService.setFromCert(false);
        this.router.navigate( ["../home"], {relativeTo: this.route ,skipLocationChange: true} );
    }

    nuovaEsenzione(){
        this.workFlowService.setFromCert(false);
        this.certWorkFlowService.resetWorkFlow();
        this.router.navigate(["../ricerca-assistito", "esen"], { relativeTo: this.route, skipLocationChange: true });
    }

}

