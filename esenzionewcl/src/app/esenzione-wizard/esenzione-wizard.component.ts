import { Component, OnInit } from '@angular/core';
import { EsenFormDataService } from "./data/esenFormData.service";
import { Input } from "@angular/core";
import { EsenWorkflowService } from "./workflow/workflow.service";
import { EsenErrorHandler } from "app/esenzione-wizard/esenErrorHandler";
import { AssistitoStoreService } from "app/assistito/assistito.service";
import { Router, ActivatedRoute } from '@angular/router';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@Component({
  selector: 'app-esenzione-wizard',
  templateUrl: './esenzione-wizard.component.html',
  styleUrls: ['./esenzione-wizard.component.css']
})
export class EsenzioneWizardComponent implements OnInit {
    title: string = 'Nuova Esenzione'

    nome: string;
    cognome: string;

    @Input() esenFormData;

    constructor(private esenFormDataService: EsenFormDataService,
            private workflowService: EsenWorkflowService, private errorHandler: EsenErrorHandler,
            private assistitoStoreService: AssistitoStoreService,
            private route: ActivatedRoute,
            private router: Router) {
    }

    ngOnInit() {
        this.nome = this.assistitoStoreService.getAssistito().nome;
        this.cognome = this.assistitoStoreService.getAssistito().cognome;
        this.esenFormData = this.esenFormDataService.getFormData();
        this.workflowService.resetWorkFlow();
        console.log(this.title + ' loaded!');
    }

    return() {
      switch(this.workflowService.getCurrentStep()) {
        case 1: {
          this.router.navigate( ["../ricerca-assistito", "esen"], {queryParams: { back:true },relativeTo: this.route,skipLocationChange: true} );
          break;
        }
        case 2: {
          this.workflowService.setCurrentStep(1);
          break;
        }
        case 3: {
          this.workflowService.setCurrentStep(2);
          break;
        }
        case 4: {
          this.workflowService.setCurrentStep(3);
          break;
        }
      }
    }
}
