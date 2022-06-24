<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
    xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">
<xsl:template match="ClinicalDocument">
  <fo:root xml:lang="it-IT" xmlns:fo="http://www.w3.org/1999/XSL/Format" font-family="Arial" font-weight="normal" font-style="normal">
      <fo:layout-master-set>
        <fo:simple-page-master master-name="simpleA4" page-height="29.7cm" page-width="21cm" margin-top="2cm" margin-bottom="2cm" margin-left="2cm" margin-right="2cm">
          <fo:region-body/>
        </fo:simple-page-master>
      </fo:layout-master-set>


      <fo:declarations>
     	 <?xpacket begin="ï»¿" id="W5M0MpCehiHzreSzNTczkc9d"?>
                <x:xmpmeta xmlns:x="adobe:ns:meta/">
                    <rdf:RDF xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#">
                        <rdf:Description rdf:about="" xmlns:dc="http://purl.org/dc/elements/1.1/">
                            <dc:title>
                                <rdf:Alt>
                                    <rdf:li xml:lang="x-default">Title</rdf:li>
                                </rdf:Alt>
                            </dc:title>
                            <dc:creator>
                                <rdf:Seq>
                                    <rdf:li>Creator</rdf:li>
                                </rdf:Seq>
                            </dc:creator>
                            <dc:description>
                                <rdf:Alt>
                                    <rdf:li xml:lang="x-default">Description</rdf:li>
                                </rdf:Alt>
                            </dc:description>
                            <dc:date>
                                <rdf:Seq>
                                    <rdf:li>2020-04-30T18:11:40+02:00</rdf:li>
                                </rdf:Seq>
                            </dc:date>                            
                        </rdf:Description>
                        <rdf:Description rdf:about=""
                                         xmlns:xmp="http://ns.adobe.com/xap/1.0/"
                                         xmlns:pdf="http://ns.adobe.com/pdf/1.3/"
                                         xmlns:pdfx="http://ns.adobe.com/pdfx/1.3/"
                                         xmlns:pdfxid="http://www.npes.org/pdfx/ns/id/">                        
                            <xmp:CreatorTool>Tool used to make the PDF</xmp:CreatorTool>
                        </rdf:Description>
                    </rdf:RDF>
                </x:xmpmeta>
                <?xpacket end="r"?>
            </fo:declarations>  
-->

      <fo:page-sequence master-reference="simpleA4">
        <fo:flow flow-name="xsl-region-body">

          <!-- Tipo documento + numero doc-->
          <fo:table table-layout="fixed" width="100%" border-collapse="separate">    
            <fo:table-column column-width="60%"/>
            <fo:table-column column-width="40%"/>
            <fo:table-body>
               <fo:table-row >  
                  <fo:table-cell text-align="right" space-after="5mm"> 
                    <fo:block font-size="16pt" font-weight="bold" >
                      <xsl:value-of select="title"/> n° 
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="left">
                    <fo:block font-size="15pt" font-weight="normal">
                      <xsl:value-of select="idAttestato"/> 
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
            </fo:table-body>
          </fo:table>

          

         <!-- Dati paziente -->
          <fo:block font-size="10pt" font-weight="bold" space-before="20mm" space-after="4mm">
            L'assistito
          </fo:block>
          <fo:block font-size="10pt">
          <fo:table table-layout="fixed" width="100%" border-collapse="separate">    
            <fo:table-column column-width="40%"/>
            <fo:table-column column-width="40%"/>
            <fo:table-column column-width="20%"/>
            <fo:table-body>
              <xsl:apply-templates select="patient"/>
            </fo:table-body>
          </fo:table>
          </fo:block>
          
           <!-- referto medico descrizione -->
          <fo:block font-size="10pt" font-weight="bold" space-before="20mm" space-after="4mm">
            In base alla documentazione prodotta, si attesta il diritto del cittadino sopra indicato a beneficiare delle seguenti
              esenzioni: 
          </fo:block>
          <fo:block font-size="10pt">
             <fo:table table-layout="fixed" width="100%" border="solid 1pt #404040">    
            <fo:table-column column-width="20%"/>
            <fo:table-column column-width="15%"/>
            <fo:table-column column-width="20%"/>
            <fo:table-column column-width="25%"/>
            <fo:table-column column-width="20%"/>
            <fo:table-body>
              <xsl:apply-templates select="tableEsenzioni"/>
            </fo:table-body>
          </fo:table>
          </fo:block>

          <!-- esenzione e diagnosi -->
          <fo:block font-size="12pt" font-weight="bold" space-before="30mm" space-after="4mm">
              Dettaglio esenzioni
          </fo:block>
          <fo:block font-size="10pt">
             <fo:table table-layout="fixed" width="100%" border="solid 1pt #404040">    
            <fo:table-column column-width="30%"/>
            <fo:table-column column-width="70%"/>
            <fo:table-body>
              <xsl:apply-templates select="components"/>
            </fo:table-body>
          </fo:table>
          </fo:block>

          <!-- data emissione e responsabile -->
         <fo:block font-size="10pt" space-before="30mm">
             <fo:table table-layout="fixed" width="100%" border-collapse="separate">    
            <fo:table-column column-width="50%"/>
            <fo:table-column column-width="50%"/>
              <fo:table-body>
               
               <fo:table-row>   
                  <fo:table-cell text-align="left">
                    <fo:block font-size="11pt" font-weight="bold">
                      Data Emissione: 
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right">
                    <fo:block font-size="11pt" font-weight="bold">
                      Il Responsabile:  
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>

                <fo:table-row>   
                  <fo:table-cell>
                    <fo:block font-size="11pt" font-weight="normal">
                      <xsl:value-of select="effectiveTime"/>
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="center">
                    <fo:block font-size="11pt" font-weight="normal">
                     
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>
                
            
            </fo:table-body>
          </fo:table>
          </fo:block>
          
            <!-- messaggio in calce -->
            
                        <!-- divider -->
         <fo:block font-size="10pt" space-before="10mm">
             <fo:table table-layout="fixed" width="100%" border-collapse="separate">    
            <fo:table-column column-width="100%"/>
              <fo:table-body>
               
               <fo:table-row>   
                  <fo:table-cell text-align="left" border-top="solid 1pt #000000">
                    <fo:block font-size="8pt">
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>

            </fo:table-body>
          </fo:table>
          </fo:block>
          
         <fo:block font-size="10pt" space-before="10mm">
             <fo:table table-layout="fixed" width="100%" border-collapse="separate">    
            <fo:table-column column-width="100%"/>
              <fo:table-body>
               
               <fo:table-row>   
                  <fo:table-cell text-align="left">
                    <fo:block font-size="8pt">
                      Informazioni sul trattamento dei dati personali ai sensi del GDPR 2016/679 - La informiamo che i suoi dati personali 
                      sono trattati secondo quanto previsto dal "Regolamento 2016/679 relativo alla protezione delle persone fisiche con 
                      riguardo al trattamento dei dati personali, nonché alla libera circolazione di tali dati e che abroga la direttiva 95/46/CE 
                      (Regolamento Generale sulla Protezione dei dati, di seguito GDPR)", per le finalità di cura o previste da normativa vigente.
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>

            </fo:table-body>
          </fo:table>
          </fo:block>

  <!-- messaggio in calce -->
         <fo:block font-size="10pt" space-before="15mm">
             <fo:table table-layout="fixed" width="100%" border-collapse="separate">    
            <fo:table-column column-width="100%"/>
              <fo:table-body>
               
               <fo:table-row>   
                  <fo:table-cell text-align="left" >
                    <fo:block font-size="8pt">
                       Il presente attestato è rilasciato da <xsl:value-of select="asl"/>
                       su richiesta dell'interessato in base alle certificazioni prodotte 
                       dai medici ed è presente nell'anagrafica regionale degli assistiti
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>

            </fo:table-body>
          </fo:table>
          </fo:block>
          
        </fo:flow>
      </fo:page-sequence>
     </fo:root>
</xsl:template>



  <!--===============================PATIENT DETAIL==============================-->



  <xsl:template match="patient">
     <fo:table-row>   
      <fo:table-cell>
        <fo:block> Cognome:  
          <xsl:value-of select="family"/>
        </fo:block>
      </fo:table-cell>
      <fo:table-cell>
        <fo:block> Nome: 
          <xsl:value-of select="given"/>
        </fo:block>
      </fo:table-cell>   
    </fo:table-row>

     <fo:table-row>   
      <fo:table-cell>
        <fo:block> Nato il:  
          <xsl:value-of select="birthTime"/>
        </fo:block>
      </fo:table-cell>
      <fo:table-cell>
        <fo:block> Codice fiscale:   
          <xsl:value-of select="id"/>
        </fo:block>
      </fo:table-cell>   
    </fo:table-row>
    
     <fo:table-row>   
      <fo:table-cell>
        <fo:block> Tessera Regionale:  
          <xsl:value-of select="card"/>
        </fo:block>
      </fo:table-cell>  
    </fo:table-row>
    
     <fo:table-row>   
      <fo:table-cell number-columns-spanned="2">
        <fo:block> Residente a:  
          <xsl:value-of select="address"/>
        </fo:block>
      </fo:table-cell>  
    </fo:table-row>
  </xsl:template>



  <!--===============================PATIENT DETAIL==============================-->


<xsl:template match="tableEsenzioni">
        <fo:table-row>   
          <fo:table-cell padding="4pt" border-bottom="solid 1pt #404040" border-right="solid 1pt #404040" border-left="solid 1pt #404040" border-top="solid 1pt #404040">
            <fo:block font-size="10pt" font-weight="bold">
              Data inizio validita
            </fo:block>
          </fo:table-cell>
          <fo:table-cell padding="4pt" border-bottom="solid 1pt #404040" border-right="solid 1pt #404040" border-left="solid 1pt #404040" border-top="solid 1pt #404040">
            <fo:block font-size="10pt" font-weight="bold">
              Codice  
            </fo:block>
          </fo:table-cell>
          <fo:table-cell padding="4pt" border-bottom="solid 1pt #404040" border-right="solid 1pt #404040" border-left="solid 1pt #404040" border-top="solid 1pt #404040">
            <fo:block font-size="10pt" font-weight="bold">
              Scadenza
            </fo:block>
          </fo:table-cell>
          <fo:table-cell padding="4pt" border-bottom="solid 1pt #404040" border-right="solid 1pt #404040" border-left="solid 1pt #404040" border-top="solid 1pt #404040">
            <fo:block font-size="10pt" font-weight="bold">
              Rif. Legislativo  
            </fo:block>
          </fo:table-cell>
          <fo:table-cell padding="4pt" border-bottom="solid 1pt #404040" border-right="solid 1pt #404040" border-left="solid 1pt #404040" border-top="solid 1pt #404040">
            <fo:block font-size="10pt" font-weight="bold">
              ASL di emissione  
            </fo:block>
          </fo:table-cell>
        </fo:table-row>


     <xsl:for-each select="detailEsenzione">
        
        <fo:table-row>  
          <fo:table-cell padding="4pt" border-bottom="solid 1pt #404040" border-right="solid 1pt #404040" border-left="solid 1pt #404040" border-top="solid 1pt #404040">
            <fo:block>
              <xsl:value-of select="startDate"/>
            </fo:block>
          </fo:table-cell> 

          <fo:table-cell padding="4pt" border-bottom="solid 1pt #404040" border-right="solid 1pt #404040" border-left="solid 1pt #404040" border-top="solid 1pt #404040">
            <fo:block>
              <xsl:value-of select="code"/>
            </fo:block>
          </fo:table-cell>

           <fo:table-cell padding="4pt" border-bottom="solid 1pt #404040" border-right="solid 1pt #404040" border-left="solid 1pt #404040" border-top="solid 1pt #404040">
            <fo:block>
              <xsl:value-of select="endDate"/>
            </fo:block>
          </fo:table-cell>   
          
          <fo:table-cell padding="4pt" border-bottom="solid 1pt #404040" border-right="solid 1pt #404040" border-left="solid 1pt #404040" border-top="solid 1pt #404040">
            <fo:block>
              <xsl:value-of select="reference"/>
            </fo:block>
          </fo:table-cell>   
     
          <fo:table-cell padding="4pt" border-bottom="solid 1pt #404040" border-right="solid 1pt #404040" border-left="solid 1pt #404040" border-top="solid 1pt #404040">
            <fo:block>
              <xsl:value-of select="codAsl"/>
            </fo:block>
          </fo:table-cell>   
        </fo:table-row>

      </xsl:for-each>
</xsl:template>


  <!--===============================COMPONENTS DETAIL==============================-->



<xsl:template match="components">

     <fo:table-row>   
          <fo:table-cell padding="4pt" border-bottom="solid 1pt #404040" border-right="solid 1pt #404040" border-left="solid 1pt #404040" border-top="solid 1pt #404040">
            <fo:block font-size="10pt" font-weight="bold">
              Codice
            </fo:block>
          </fo:table-cell>
          <fo:table-cell padding="4pt" border-bottom="solid 1pt #404040" border-right="solid 1pt #404040" border-left="solid 1pt #404040" border-top="solid 1pt #404040">
            <fo:block font-size="10pt" font-weight="bold">
              Prestazioni Esenti  
            </fo:block>
          </fo:table-cell>
        </fo:table-row>

   <xsl:for-each select="component">
      <fo:table-row>   
        <fo:table-cell padding="4pt" border-bottom="solid 1pt #404040" border-right="solid 1pt #404040" border-left="solid 1pt #404040" border-top="solid 1pt #404040">
          <fo:block>
            <xsl:value-of select="code"/>
          </fo:block>
        </fo:table-cell>
   
        <fo:table-cell padding="4pt" border-bottom="solid 1pt #404040" border-right="solid 1pt #404040" border-left="solid 1pt #404040" border-top="solid 1pt #404040">
          <fo:block>
            <xsl:value-of select="description"/>
          </fo:block>
        </fo:table-cell>   
      </fo:table-row>
    </xsl:for-each>
</xsl:template>


</xsl:stylesheet> 