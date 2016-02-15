package com.uzabase.checker.crawler;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.util.UrlUtils;

import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Created by Trung on 2/1/2016 8:45 PM.
 * Copyright  © 2016 Uzabase Inc. All rights reserved.
 */
public class FuzzyPage implements Serializable {

    private Page fuzzyPage;
    private URI baseURI;
    private HashSet<URI> fuzzyPageURIsSet;
    private List<FuzzyForm> fuzzyPageForms;
    private Map<String, List<String>> urlParams;
    private boolean guessed;

    public FuzzyPage(Page fuzzyPage) {
        this.fuzzyPage = fuzzyPage;
        fuzzyPageForms = new ArrayList<FuzzyForm>();
        fuzzyPageURIsSet = new HashSet<URI>();
        urlParams = new HashMap<String, List<String>>();
        guessed = false;

        try {
            baseURI = fuzzyPage.getUrl().toURI();
            discoverPageURIs();
            discoverForms();
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public boolean isGuessed() {
        return guessed;
    }

    public void setGuessed(boolean guessed) {
        this.guessed = guessed;
    }

    public String toString() {
        return baseURI.toString();
    }

    public URI getPageURI() {
        return baseURI;
    }

    public String getUnescapedPageURL() {
        return UrlUtils.decode(fuzzyPage.getUrl().toExternalForm());
    }

    public HashSet<URI> getAllPageURIs() {
        return fuzzyPageURIsSet;
    }

    public Set<String> getAllURLParamsNoValues() {
        return urlParams.keySet();
    }

    public List<FuzzyForm> getAllForms() {
        return fuzzyPageForms;
    }

    private void discoverForms() {
        try {
            HtmlPage castPage = (HtmlPage) fuzzyPage;
            for (HtmlForm f : castPage.getForms()) {
                fuzzyPageForms.add(new FuzzyForm(f));
            }
        } catch (ClassCastException cce) {
            System.err.println("Invalid Class Cast: " + cce.getMessage());
        }

    }

    private void discoverPageURIs() throws URISyntaxException {
        try {
            HtmlPage castPage = (HtmlPage) fuzzyPage;
            List<HtmlAnchor> pageAnchors = castPage.getAnchors();
            String baseHost = baseURI.getHost();

            for (HtmlAnchor link : pageAnchors) {
                URI discoveredURI;
                if (link.getHrefAttribute().equals("")) {
                    HtmlPage nextPage = link.click();
                    discoveredURI = nextPage.getUrl().toURI();
                    System.out.println(discoveredURI);
                } else {
                    String uriHref = link.getHrefAttribute().replace(" ",
                            "%20");
                    discoveredURI = new URI(uriHref);
                }

                String discoveredHost = discoveredURI.getHost();

                if ((discoveredHost != null && baseHost
                        .compareTo(discoveredHost) == 0)
                        || (discoveredHost == null && (discoveredURI
                        .getPath() != null && discoveredURI.getPath()
                        .length() > 0))) {

                    URI resolvedURI = baseURI.resolve(discoveredURI);
                    URI strippedResolvedURI = new URI(
                            resolvedURI.getScheme(),
                            resolvedURI.getAuthority(),
                            resolvedURI.getPath(), null, null);

                    String query = resolvedURI.getRawQuery();

                    if (query != null) {
                        String[] params = query.split("&");

                        for (String param : params) {
                            String[] values = param.split("=");

                            if (urlParams.containsKey(values[0])) {
                                urlParams.get(values[0]).add(values[1]);
                            } else {
                                ArrayList<String> valid = new ArrayList<String>();
                                valid.add(values[1]);

                                urlParams.put(values[0], valid);
                            }
                        }
                    }

                    fuzzyPageURIsSet.add(strippedResolvedURI);
                }
            }
        } catch (ClassCastException cce) {
            System.err.println("Invalid Class Cast: " + cce.getMessage());
        } catch (Exception horriblePractice) {
            System.err.println("Yeah, we're screwed: "
                    + horriblePractice.getMessage());
        }
    }
}
