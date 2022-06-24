import { Injectable } from "@angular/core";
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@Injectable()
export class EsenErrorHandler {

    hidden: boolean = true;
    errorMessage: string = '';

    constructor() { }

    setErrorMessage(errorMessage: string){
        this.errorMessage = errorMessage;
    }

    setHidden(bool: boolean){
        this.hidden = bool;
    }
}
