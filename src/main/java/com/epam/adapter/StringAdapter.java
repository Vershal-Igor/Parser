package com.epam.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class StringAdapter extends XmlAdapter<String, String> {

    @Override
    public String marshal(String text) {
        return text.trim();
    }

    @Override
    public String unmarshal(String v) throws Exception {
        return v.trim();
    }
}
