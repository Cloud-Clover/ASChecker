package com.uzabase.checker.crawler;

import com.gargoylesoftware.htmlunit.html.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an HTML form within an HTML page.
 *
 * Created by Intelligent on 2/1/2016.
 */
public class FuzzyForm implements Serializable{

    private HtmlForm fuzzyForm;
    private List<HtmlElement> lostChildrenElements;
    private List<HtmlElement> htmlInputs = new ArrayList<HtmlElement>();
    private List<HtmlSubmitInput> submitInputs = new ArrayList<HtmlSubmitInput>();

    public FuzzyForm(HtmlForm fuzzyForm) {
        this.fuzzyForm = fuzzyForm;

        getAllInputs();
        for(DomNode n : fuzzyForm.getChildren()){
            discoverInputs(n);
        }
        discoverLostChildrenElements();
    }

    public List<HtmlElement> getAllInputs() {
        return htmlInputs;
    }

    public List<HtmlSubmitInput> getSubmitButton() {
        return submitInputs;
    }

    public List<HtmlElement> getLostChildren(){
        return lostChildrenElements;
    }

    private void discoverLostChildrenElements(){
        lostChildrenElements = fuzzyForm.getLostChildren();
    }

    private void discoverInputs(DomNode node){
        for( DomNode n : node.getChildren()){
            if( n instanceof HtmlTextInput){
                htmlInputs.add((HtmlInput)n);

            }else if(n instanceof HtmlHiddenInput){
                htmlInputs.add((HtmlInput)n);

            }else if(n instanceof HtmlFileInput){
                htmlInputs.add((HtmlInput)n);

            }else if(n instanceof HtmlImageInput){
                htmlInputs.add((HtmlInput)n);

            }else if(n instanceof HtmlPasswordInput){
                htmlInputs.add((HtmlInput)n);

            }else if(n instanceof HtmlSubmitInput){
                submitInputs.add((HtmlSubmitInput)n);

            }else if(n instanceof HtmlTextArea) {
                htmlInputs.add((HtmlTextArea)n);
            }

            if (n.hasChildNodes()){
                discoverInputs(n);
            }
        }
    }

    @Override
    public String toString() {
        return "FuzzyForm{" +
                "fuzzyForm=" + fuzzyForm +
                ", lostChildrenElements=" + lostChildrenElements +
                ", htmlInputs=" + htmlInputs +
                ", submitInputs=" + submitInputs +
                '}';
    }
}
