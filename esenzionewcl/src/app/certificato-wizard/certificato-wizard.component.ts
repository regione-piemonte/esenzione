import { Component, OnInit } from '@angular/core';
import { FormDataService } from "./data/formData.service";
import { Input } from "@angular/core";
import { WorkflowService } from "app/certificato-wizard/workflow/workflow.service";
import { RouterModule, ActivatedRoute, Router } from '@angular/router';
import { AssistitoStoreService } from "app/assistito/assistito.service";
import { Assistito } from "app/assistito/assistito";
import { CertErrorHandler } from "app/certificato-wizard/certErrorHandler";
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@Component({
  selector: 'app-certificato-wizard',
  templateUrl: './certificato-wizard.component.html',
  styleUrls: ['./certificato-wizard.component.css']
})
export class CertificatoWizardComponent implements OnInit {
    title: string = 'Nuovo Certificato'
    assistito: Assistito;

    @Input() formData;

    constructor(private formDataService: FormDataService, private workflowService: WorkflowService,
            private assistitoStoreService: AssistitoStoreService,
            private route: ActivatedRoute,
            private router: Router,
            private errorHandler: CertErrorHandler) {
    }

    ngOnInit() {
        this.errorHandler.setHidden(true);
        this.assistito = this.assistitoStoreService.getAssistito();
        this.formData = this.formDataService.getFormData();
//        this.workflowService.resetWorkFlow();
    }

    return() {
      switch(this.workflowService.getCurrentStep()) {
        case 1: {
          this.router.navigate( ["../ricerca-assistito", "cert"], {queryParams: { back:true },relativeTo: this.route,skipLocationChange: true} );
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

      }
    }
}
