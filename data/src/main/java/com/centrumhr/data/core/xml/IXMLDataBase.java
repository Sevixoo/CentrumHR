package com.centrumhr.data.core.xml;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import java.util.List;
import java.util.UUID;

/**
 * Created by Seweryn on 24.05.2017.
 */
public interface IXMLDataBase {

    Element create( String tag );

    void insert( Element item );

    Node find(String nodeType , UUID uuid);

    List<Element> list();

    void delete(  UUID uuid );

    void beginTransaction();

    void endTransaction();

}
