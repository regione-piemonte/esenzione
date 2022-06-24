import { Pipe, PipeTransform } from '@angular/core';
import * as _ from 'lodash';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
@Pipe({
  name: 'esenzioneunique',
  pure: false
})

export class EsenzioneUniquePipe implements PipeTransform {
    transform(value: any): any{
        if(value!== undefined && value!== null){

            return _.uniqBy(value, 'nomeBeneficiario','cognomeBeneficiario');

        }

        return value;
    }

}
