import { FiltriRicercaPratiche } from "app/esenzione/FiltriRicercaPratiche";
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
export class TabService{

    private currentTab: string = '';
    private returnFrom: boolean = false;
    public lastSearch = {
        filtri: new FiltriRicercaPratiche(),
        numeroElementiPagina: 0,
        pagina: 0,
        lunghezzaarray: 0,
        numerototpagine: [],
        numerovaloriperpagina: 0,
        numeroTotaleElementi: 0,
        listaPratiche: [],
        selezionaTutti: false,
        listadavalidarepratica: [],
        parametroTmp: [],
        statoIndex: 0,
        codEsenIndex: 0
    }
    constructor() {}

    getCurrentTab(): string {
        return this.currentTab;
    }

    setCurrentTab(tab: string) {
        this.currentTab = tab;
    }

    getReturnFrom(): boolean {
        return this.returnFrom;
    }

    setReturnFrom(on: boolean) {
        this.returnFrom = on;
    }

}
