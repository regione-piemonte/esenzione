import { Component, OnInit } from '@angular/core';
import { WorkflowService } from "app/certificato-wizard/workflow/workflow.service";
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@Component ({
    selector: 'app-cert-navbar'
    ,templateUrl: './navbar.component.html'
})

export class NavbarComponent implements OnInit {

    constructor(private workflowService: WorkflowService) { }

    ngOnInit(){

    }
}
