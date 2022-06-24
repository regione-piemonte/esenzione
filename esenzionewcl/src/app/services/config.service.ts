import { Injectable } from '@angular/core'
import { environment } from "../../environments/environment"
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@Injectable()
export class ConfigService {


    getBEServer(): string {
        try { if ( ENV_PROPERTIES.beServer != null ) return ENV_PROPERTIES.beServer; }
        catch ( e ) { return environment.beServer; }

    }

    getBEServerContext(): string {
        try { if ( ENV_PROPERTIES.contextRestBeServer != null ) return ENV_PROPERTIES.contextRestBeServer; }
        catch ( e ) { return environment.contextRestBeServer; }

    }

    getdevBackendServer(): string {
        try { if ( ENV_PROPERTIES.devBackendServer != null ) return ENV_PROPERTIES.devBackendServer; }
        catch ( e ) { return environment.devBackendServer; }
    }

    getPrenotazioni(): string {
        try { if ( ENV_PROPERTIES.prenotazioni != null ) return ENV_PROPERTIES.prenotazioni; }
        catch ( e ) { return environment.prenotazioni; }

    }

    getDeleghe(): string {
        try { if ( ENV_PROPERTIES.deleghe != null ) return ENV_PROPERTIES.deleghe; }
        catch ( e ) { return environment.deleghe; }

    }
    getTicket(): string {
        try { if ( ENV_PROPERTIES.ticket != null ) return ENV_PROPERTIES.ticket; }
        catch ( e ) { return environment.ticket; }

    }

    getSalute(): string {
        try { if ( ENV_PROPERTIES.salute != null ) return ENV_PROPERTIES.salute; }
        catch ( e ) { return environment.salute; }

    }

    getReferti(): string {
        try { if ( ENV_PROPERTIES.referti != null ) return ENV_PROPERTIES.referti; }
        catch ( e ) { return environment.referti; }

    }

    getFascicolos(): string {
        try { if ( ENV_PROPERTIES.fascicolos != null ) return ENV_PROPERTIES.fascicolos; }
        catch ( e ) { return environment.fascicolos; }

    }

    getTaccuino(): string {
        try { if ( ENV_PROPERTIES.taccuino != null ) return ENV_PROPERTIES.taccuino; }
        catch ( e ) { return environment.taccuino; }

    }

    getFascicolof(): string {
        try { if ( ENV_PROPERTIES.fascicolof != null ) return ENV_PROPERTIES.fascicolof; }
        catch ( e ) { return environment.fascicolof; }

    }

    getScreening(): string {
        try { if ( ENV_PROPERTIES.screening != null ) return ENV_PROPERTIES.screening; }
        catch ( e ) { return environment.screening; }

    }

    getDocumenti(): string {
        try { if ( ENV_PROPERTIES.documenti != null ) return ENV_PROPERTIES.documenti; }
        catch ( e ) { return environment.documenti; }

    }

    getCambiomedico(): string {
        try { if ( ENV_PROPERTIES.cambiomedico != null ) return ENV_PROPERTIES.cambiomedico; }
        catch ( e ) { return environment.cambiomedico; }

    }

    getMailAssistenza(): string {
        try { if ( ENV_PROPERTIES.mailassistenza != null ) return ENV_PROPERTIES.mailassistenza; }
        catch ( e ) { return environment.mailassistenza; }

    }

    getMailAssistenzaPa(): string {
        try { if ( ENV_PROPERTIES.mailassistenzapa != null ) return ENV_PROPERTIES.mailassistenzapa; }
        catch ( e ) { return environment.mailassistenzapa; }
    }

    getTrova(): string {
        try { if ( ENV_PROPERTIES.trova != null ) return ENV_PROPERTIES.trova; }
        catch ( e ) { return environment.trova; }
    }

    getValuta(): string {
        try { if ( ENV_PROPERTIES.valuta != null ) return ENV_PROPERTIES.valuta; }
        catch ( e ) { return environment.valuta; }
    }

    getProfilo(): string {
        try { if ( ENV_PROPERTIES.profilo != null ) return ENV_PROPERTIES.profilo; }
        catch ( e ) { return environment.profilo; }
    }

    getOnAppExitURL(): string {
        try { if ( ENV_PROPERTIES.onAppExitURL != null ) return ENV_PROPERTIES.onAppExitURL; }
        catch ( e ) { return environment.onAppExitURL; }

    }

    getOnAppExitURLOperatore(): string {
        try { if ( ENV_PROPERTIES.onAppExitURLOperatore != null ) return ENV_PROPERTIES.onAppExitURLOperatore; }
        catch ( e ) { return environment.onAppExitURLOperatore; }

    }

    getOnAppExitURLCittadino(): string {
        try { if ( ENV_PROPERTIES.onAppExitURLCittadino != null ) return ENV_PROPERTIES.onAppExitURLCittadino; }
        catch ( e ) { return environment.onAppExitURLCittadino; }

    }
}
