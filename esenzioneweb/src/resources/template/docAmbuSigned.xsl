<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.1" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
    xmlns:fo="http://www.w3.org/1999/XSL/Format" exclude-result-prefixes="fo">
<xsl:template match="ClinicalDocument">
  <fo:root xml:lang="it" xmlns:fo="http://www.w3.org/1999/XSL/Format" font-family="Arial" font-weight="normal" font-style="normal">
      <fo:layout-master-set>
        <fo:simple-page-master master-name="simpleA4" page-height="29.7cm" page-width="21cm" margin-top="2cm" margin-bottom="2cm" margin-left="2cm" margin-right="2cm">
          <fo:region-body/>
        </fo:simple-page-master>
      </fo:layout-master-set>

      <fo:declarations>
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
            </fo:declarations>   


      <fo:page-sequence master-reference="simpleA4">
        <fo:flow flow-name="xsl-region-body">


		<!-- intestazione -->
		  <fo:block font-size="6pt" text-align="right" space-after="10mm" color="#999999">      
		  <fo:table table-layout="fixed" width="100%" border-collapse="separate">    
            <fo:table-body>
             <fo:table-row>   
		      <fo:table-cell>
		        <fo:block> 
           		  <xsl:value-of select="headerAsl"/>
		        </fo:block>
		      </fo:table-cell>
    		 </fo:table-row>
             <fo:table-row>       
		      <fo:table-cell>
		        <fo:block>
         		   <xsl:value-of select="headerAddress"/>
		        </fo:block>
		      </fo:table-cell>   
			 </fo:table-row>
			  <fo:table-row>   
		      <fo:table-cell>
		        <fo:block> 
           		  <xsl:value-of select="headerContact"/>
		        </fo:block>
		      </fo:table-cell>
    		 </fo:table-row>
    		  <fo:table-row>   
		      <fo:table-cell>
		        <fo:block> 
           		  <xsl:value-of select="headerLegal"/>
		        </fo:block>
		      </fo:table-cell>
    		 </fo:table-row>
		     </fo:table-body>
          </fo:table>
          </fo:block>
                  
          <!-- titolo documento -->
          <fo:block font-size="16pt" font-weight="bold" text-align="center" space-after="20mm">
            <xsl:value-of select="title"/>
          </fo:block>

         <!-- Dati paziente -->
          <fo:block font-size="10pt" font-weight="bold" space-after="4mm">
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
          <fo:block font-size="10pt" font-weight="bold" space-before="40mm" space-after="4mm">
            è affetto dalla seguente patologia/malattia rara/invalidità:   
          </fo:block>
          <fo:block font-size="10pt">
             <fo:table table-layout="fixed" width="100%" border-collapse="separate">    
            <fo:table-column column-width="100%"/>
            <fo:table-body>
              <xsl:apply-templates select="report"/>
            </fo:table-body>
          </fo:table>
          </fo:block>

          <!-- esenzione e diagnosi -->
          <fo:block font-size="10pt" font-weight="bold" space-before="40mm" space-after="4mm">
            Pertanto, si certifica il diritto del cittadino sopraindicato a beneficiare della seguente esenzione: 
          </fo:block>
          <fo:block font-size="10pt">
             <fo:table table-layout="fixed" width="100%" border="solid 1pt #404040">    
            <fo:table-column column-width="35%"/>
            <fo:table-column column-width="20%"/>
            <fo:table-column column-width="45%"/>
            <fo:table-body>
              <xsl:apply-templates select="component"/>
            </fo:table-body>
          </fo:table>
          </fo:block>

          <!-- data emissione e responsabile -->
         <fo:block font-size="10pt" space-before="40mm">
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
                      Il Medico di Medicina Generale o  
                    </fo:block>                    
                    <fo:block font-size="11pt" font-weight="bold">
                      Medico Specialista di struttura pubblica:
                    </fo:block>
                  </fo:table-cell>
                </fo:table-row>

                <fo:table-row>   
                  <fo:table-cell>
                    <fo:block font-size="11pt" font-weight="normal">
                      <xsl:value-of select="effectiveTime"/>
                    </fo:block>
                  </fo:table-cell>
                  <fo:table-cell text-align="right">
                    <xsl:apply-templates select="author"/>

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
  </xsl:template>



  <!--===============================DISEASE DETAIL==============================-->


  <xsl:template match="report">
     <fo:table-row>   
      <fo:table-cell>
        <fo:block>
          <xsl:value-of select="description"/>
        </fo:block>
      </fo:table-cell>  
    </fo:table-row>
  </xsl:template>


  <!--===============================COMPONENTS DETAIL==============================-->



<xsl:template match="component">
    <fo:table-row>  
      <fo:table-cell padding="4pt" border-bottom="solid 1pt #404040" border-right="solid 1pt #404040" border-left="solid 1pt #404040" border-top="solid 1pt #404040">
        <fo:block>
          <xsl:value-of select="title"/>
        </fo:block>
      </fo:table-cell>  
      
      <fo:table-cell padding="4pt" border-bottom="solid 1pt #404040" border-right="solid 1pt #404040" border-left="solid 1pt #404040" border-top="solid 1pt #404040">
        <fo:block>
          <xsl:value-of select="code"/>
        </fo:block>
      </fo:table-cell>
 
      <fo:table-cell padding="4pt" border-bottom="solid 1pt #404040" border-right="solid 1pt #404040" border-left="solid 1pt #404040" border-top="solid 1pt #404040">
        <fo:block>
          <xsl:value-of select="name"/>
        </fo:block>
      </fo:table-cell>   

    </fo:table-row>
</xsl:template>


  <!--===============================AUTHOR DETAIL==============================-->

  <xsl:template match="author">
   <fo:block font-size="11pt" font-weight="normal">
        <xsl:value-of select="family"/> <xsl:text> </xsl:text> <xsl:value-of select="given"/>
   </fo:block>
     <fo:block font-size="7pt" font-weight="normal">
        firmato digitalmente
   </fo:block>
  
  </xsl:template>

</xsl:stylesheet> 