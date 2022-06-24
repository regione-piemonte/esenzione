import { HttpClientBasicsPage } from './app.po';
/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

describe('http-client-basics App', () => {
  let page: HttpClientBasicsPage;

  beforeEach(() => {
    page = new HttpClientBasicsPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
