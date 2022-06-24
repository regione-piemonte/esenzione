import { Component, OnInit } from '@angular/core';
import { EsenWorkflowService } from "../workflow/workflow.service";
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@Component ({
    selector: 'app-esen-navbar'
    ,templateUrl: './navbar.component.html'
})

export class EsenNavbarComponent implements OnInit {

    constructor(private workflowService: EsenWorkflowService) { }

    ngOnInit(){

    }
}
