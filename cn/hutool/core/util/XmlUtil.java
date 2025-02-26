package cn.hutool.core.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.BiMap;
import cn.hutool.core.map.MapUtil;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.namespace.NamespaceContext;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

public class XmlUtil {
  public static final String NBSP = "&nbsp;";
  
  public static final String AMP = "&amp;";
  
  public static final String QUOTE = "&quot;";
  
  public static final String APOS = "&apos;";
  
  public static final String LT = "&lt;";
  
  public static final String GT = "&gt;";
  
  public static final String INVALID_REGEX = "[\\x00-\\x08\\x0b-\\x0c\\x0e-\\x1f]";
  
  public static final String COMMENT_REGEX = "(?s)<!--.+?-->";
  
  public static final int INDENT_DEFAULT = 2;
  
  private static String defaultDocumentBuilderFactory = "com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl";
  
  private static boolean namespaceAware = true;
  
  private static SAXParserFactory factory;
  
  public static synchronized void disableDefaultDocumentBuilderFactory() {
    defaultDocumentBuilderFactory = null;
  }
  
  public static synchronized void setNamespaceAware(boolean isNamespaceAware) {
    namespaceAware = isNamespaceAware;
  }
  
  public static Document readXML(File file) {
    Assert.notNull(file, "Xml file is null !", new Object[0]);
    if (false == file.exists())
      throw new UtilException("File [{}] not a exist!", new Object[] { file.getAbsolutePath() }); 
    if (false == file.isFile())
      throw new UtilException("[{}] not a file!", new Object[] { file.getAbsolutePath() }); 
    try {
      file = file.getCanonicalFile();
    } catch (IOException iOException) {}
    BufferedInputStream in = null;
    try {
      in = FileUtil.getInputStream(file);
      return readXML(in);
    } finally {
      IoUtil.close(in);
    } 
  }
  
  public static Document readXML(String pathOrContent) {
    if (StrUtil.startWith(pathOrContent, '<'))
      return parseXml(pathOrContent); 
    return readXML(FileUtil.file(pathOrContent));
  }
  
  public static Document readXML(InputStream inputStream) throws UtilException {
    return readXML(new InputSource(inputStream));
  }
  
  public static Document readXML(Reader reader) throws UtilException {
    return readXML(new InputSource(reader));
  }
  
  public static Document readXML(InputSource source) {
    DocumentBuilder builder = createDocumentBuilder();
    try {
      return builder.parse(source);
    } catch (Exception e) {
      throw new UtilException(e, "Parse XML from stream error!", new Object[0]);
    } 
  }
  
  public static void readBySax(File file, ContentHandler contentHandler) {
    InputStream in = null;
    try {
      in = FileUtil.getInputStream(file);
      readBySax(new InputSource(in), contentHandler);
    } finally {
      IoUtil.close(in);
    } 
  }
  
  public static void readBySax(Reader reader, ContentHandler contentHandler) {
    try {
      readBySax(new InputSource(reader), contentHandler);
    } finally {
      IoUtil.close(reader);
    } 
  }
  
  public static void readBySax(InputStream source, ContentHandler contentHandler) {
    try {
      readBySax(new InputSource(source), contentHandler);
    } finally {
      IoUtil.close(source);
    } 
  }
  
  public static void readBySax(InputSource source, ContentHandler contentHandler) {
    if (null == factory) {
      factory = SAXParserFactory.newInstance();
      factory.setValidating(false);
      factory.setNamespaceAware(namespaceAware);
      try {
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
      } catch (Exception exception) {}
    } 
    try {
      SAXParser parse = factory.newSAXParser();
      if (contentHandler instanceof DefaultHandler) {
        parse.parse(source, (DefaultHandler)contentHandler);
        return;
      } 
      XMLReader reader = parse.getXMLReader();
      reader.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
      reader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
      reader.setFeature("http://xml.org/sax/features/external-general-entities", false);
      reader.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
      reader.setContentHandler(contentHandler);
      reader.parse(source);
    } catch (ParserConfigurationException|org.xml.sax.SAXException e) {
      throw new UtilException(e);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public static Document parseXml(String xmlStr) {
    if (StrUtil.isBlank(xmlStr))
      throw new IllegalArgumentException("XML content string is empty !"); 
    xmlStr = cleanInvalid(xmlStr);
    return readXML(StrUtil.getReader(xmlStr));
  }
  
  public static String toStr(Node doc) {
    return toStr(doc, false);
  }
  
  public static String toStr(Document doc) {
    return toStr(doc);
  }
  
  public static String toStr(Node doc, boolean isPretty) {
    return toStr(doc, "UTF-8", isPretty);
  }
  
  public static String toStr(Document doc, boolean isPretty) {
    return toStr(doc, isPretty);
  }
  
  public static String toStr(Node doc, String charset, boolean isPretty) {
    return toStr(doc, charset, isPretty, false);
  }
  
  public static String toStr(Document doc, String charset, boolean isPretty) {
    return toStr(doc, charset, isPretty);
  }
  
  public static String toStr(Node doc, String charset, boolean isPretty, boolean omitXmlDeclaration) {
    StringWriter writer = StrUtil.getWriter();
    try {
      write(doc, writer, charset, isPretty ? 2 : 0, omitXmlDeclaration);
    } catch (Exception e) {
      throw new UtilException(e, "Trans xml document to string error!", new Object[0]);
    } 
    return writer.toString();
  }
  
  public static String format(Document doc) {
    return toStr(doc, true);
  }
  
  public static String format(String xmlStr) {
    return format(parseXml(xmlStr));
  }
  
  public static void toFile(Document doc, String absolutePath) {
    toFile(doc, absolutePath, null);
  }
  
  public static void toFile(Document doc, String path, String charsetName) {
    if (StrUtil.isBlank(charsetName))
      charsetName = doc.getXmlEncoding(); 
    if (StrUtil.isBlank(charsetName))
      charsetName = "UTF-8"; 
    BufferedWriter writer = null;
    try {
      writer = FileUtil.getWriter(path, CharsetUtil.charset(charsetName), false);
      write(doc, writer, charsetName, 2);
    } finally {
      IoUtil.close(writer);
    } 
  }
  
  public static void write(Node node, Writer writer, String charset, int indent) {
    transform(new DOMSource(node), new StreamResult(writer), charset, indent);
  }
  
  public static void write(Node node, Writer writer, String charset, int indent, boolean omitXmlDeclaration) {
    transform(new DOMSource(node), new StreamResult(writer), charset, indent, omitXmlDeclaration);
  }
  
  public static void write(Node node, OutputStream out, String charset, int indent) {
    transform(new DOMSource(node), new StreamResult(out), charset, indent);
  }
  
  public static void write(Node node, OutputStream out, String charset, int indent, boolean omitXmlDeclaration) {
    transform(new DOMSource(node), new StreamResult(out), charset, indent, omitXmlDeclaration);
  }
  
  public static void transform(Source source, Result result, String charset, int indent) {
    transform(source, result, charset, indent, false);
  }
  
  public static void transform(Source source, Result result, String charset, int indent, boolean omitXmlDeclaration) {
    TransformerFactory factory = TransformerFactory.newInstance();
    try {
      Transformer xformer = factory.newTransformer();
      if (indent > 0) {
        xformer.setOutputProperty("indent", "yes");
        xformer.setOutputProperty("doctype-public", "yes");
        xformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", String.valueOf(indent));
      } 
      if (StrUtil.isNotBlank(charset))
        xformer.setOutputProperty("encoding", charset); 
      if (omitXmlDeclaration)
        xformer.setOutputProperty("omit-xml-declaration", "yes"); 
      xformer.transform(source, result);
    } catch (Exception e) {
      throw new UtilException(e, "Trans xml document to string error!", new Object[0]);
    } 
  }
  
  public static Document createXml() {
    return createDocumentBuilder().newDocument();
  }
  
  public static DocumentBuilder createDocumentBuilder() {
    DocumentBuilder builder;
    try {
      builder = createDocumentBuilderFactory().newDocumentBuilder();
    } catch (Exception e) {
      throw new UtilException(e, "Create xml document error!", new Object[0]);
    } 
    return builder;
  }
  
  public static DocumentBuilderFactory createDocumentBuilderFactory() {
    DocumentBuilderFactory factory;
    if (StrUtil.isNotEmpty(defaultDocumentBuilderFactory)) {
      factory = DocumentBuilderFactory.newInstance(defaultDocumentBuilderFactory, null);
    } else {
      factory = DocumentBuilderFactory.newInstance();
    } 
    factory.setNamespaceAware(namespaceAware);
    return disableXXE(factory);
  }
  
  public static Document createXml(String rootElementName) {
    return createXml(rootElementName, null);
  }
  
  public static Document createXml(String rootElementName, String namespace) {
    Document doc = createXml();
    doc.appendChild((null == namespace) ? doc.createElement(rootElementName) : doc.createElementNS(namespace, rootElementName));
    return doc;
  }
  
  public static Element getRootElement(Document doc) {
    return (null == doc) ? null : doc.getDocumentElement();
  }
  
  public static Document getOwnerDocument(Node node) {
    return (node instanceof Document) ? (Document)node : node.getOwnerDocument();
  }
  
  public static String cleanInvalid(String xmlContent) {
    if (xmlContent == null)
      return null; 
    return xmlContent.replaceAll("[\\x00-\\x08\\x0b-\\x0c\\x0e-\\x1f]", "");
  }
  
  public static String cleanComment(String xmlContent) {
    if (xmlContent == null)
      return null; 
    return xmlContent.replaceAll("(?s)<!--.+?-->", "");
  }
  
  public static List<Element> getElements(Element element, String tagName) {
    NodeList nodeList = StrUtil.isBlank(tagName) ? element.getChildNodes() : element.getElementsByTagName(tagName);
    return transElements(element, nodeList);
  }
  
  public static Element getElement(Element element, String tagName) {
    NodeList nodeList = element.getElementsByTagName(tagName);
    int length = nodeList.getLength();
    if (length < 1)
      return null; 
    for (int i = 0; i < length; i++) {
      Element childEle = (Element)nodeList.item(i);
      if (childEle == null || childEle.getParentNode() == element)
        return childEle; 
    } 
    return null;
  }
  
  public static String elementText(Element element, String tagName) {
    Element child = getElement(element, tagName);
    return (child == null) ? null : child.getTextContent();
  }
  
  public static String elementText(Element element, String tagName, String defaultValue) {
    Element child = getElement(element, tagName);
    return (child == null) ? defaultValue : child.getTextContent();
  }
  
  public static List<Element> transElements(NodeList nodeList) {
    return transElements(null, nodeList);
  }
  
  public static List<Element> transElements(Element parentEle, NodeList nodeList) {
    int length = nodeList.getLength();
    ArrayList<Element> elements = new ArrayList<>(length);
    for (int i = 0; i < length; i++) {
      Node node = nodeList.item(i);
      if (1 == node.getNodeType()) {
        Element element = (Element)nodeList.item(i);
        if (parentEle == null || element.getParentNode() == parentEle)
          elements.add(element); 
      } 
    } 
    return elements;
  }
  
  public static void writeObjectAsXml(File dest, Object bean) {
    XMLEncoder xmlenc = null;
    try {
      xmlenc = new XMLEncoder(FileUtil.getOutputStream(dest));
      xmlenc.writeObject(bean);
    } finally {
      IoUtil.close(xmlenc);
    } 
  }
  
  public static XPath createXPath() {
    return XPathFactory.newInstance().newXPath();
  }
  
  public static Element getElementByXPath(String expression, Object source) {
    return (Element)getNodeByXPath(expression, source);
  }
  
  public static NodeList getNodeListByXPath(String expression, Object source) {
    return (NodeList)getByXPath(expression, source, XPathConstants.NODESET);
  }
  
  public static Node getNodeByXPath(String expression, Object source) {
    return (Node)getByXPath(expression, source, XPathConstants.NODE);
  }
  
  public static Object getByXPath(String expression, Object source, QName returnType) {
    NamespaceContext nsContext = null;
    if (source instanceof Node)
      nsContext = new UniversalNamespaceCache((Node)source, false); 
    return getByXPath(expression, source, returnType, nsContext);
  }
  
  public static Object getByXPath(String expression, Object source, QName returnType, NamespaceContext nsContext) {
    XPath xPath = createXPath();
    if (null != nsContext)
      xPath.setNamespaceContext(nsContext); 
    try {
      if (source instanceof InputSource)
        return xPath.evaluate(expression, (InputSource)source, returnType); 
      return xPath.evaluate(expression, source, returnType);
    } catch (XPathExpressionException e) {
      throw new UtilException(e);
    } 
  }
  
  public static String escape(String string) {
    return EscapeUtil.escapeHtml4(string);
  }
  
  public static String unescape(String string) {
    return EscapeUtil.unescapeHtml4(string);
  }
  
  public static Map<String, Object> xmlToMap(String xmlStr) {
    return xmlToMap(xmlStr, new HashMap<>());
  }
  
  public static <T> T xmlToBean(Node node, Class<T> bean) {
    Map<String, Object> map = xmlToMap(node);
    if (null != map && map.size() == 1) {
      String simpleName = bean.getSimpleName();
      String nodeName = (String)CollUtil.getFirst(map.keySet());
      if (simpleName.equalsIgnoreCase(nodeName))
        return (T)BeanUtil.toBean(map.get(nodeName), bean); 
    } 
    return (T)BeanUtil.toBean(map, bean);
  }
  
  public static Map<String, Object> xmlToMap(Node node) {
    return xmlToMap(node, new HashMap<>());
  }
  
  public static Map<String, Object> xmlToMap(String xmlStr, Map<String, Object> result) {
    Document doc = parseXml(xmlStr);
    Element root = getRootElement(doc);
    root.normalize();
    return xmlToMap(root, result);
  }
  
  public static Map<String, Object> xmlToMap(Node node, Map<String, Object> result) {
    if (null == result)
      result = new HashMap<>(); 
    NodeList nodeList = node.getChildNodes();
    int length = nodeList.getLength();
    for (int i = 0; i < length; i++) {
      Node childNode = nodeList.item(i);
      if (false != isElement(childNode)) {
        Object newValue;
        Element childEle = (Element)childNode;
        Object value = result.get(childEle.getNodeName());
        if (childEle.hasChildNodes()) {
          Map<String, Object> map = xmlToMap(childEle);
          if (MapUtil.isNotEmpty(map)) {
            newValue = map;
          } else {
            newValue = childEle.getTextContent();
          } 
        } else {
          newValue = childEle.getTextContent();
        } 
        if (null != newValue)
          if (null != value) {
            if (value instanceof List) {
              ((List<Object>)value).add(newValue);
            } else {
              result.put(childEle.getNodeName(), CollUtil.newArrayList(new Object[] { value, newValue }));
            } 
          } else {
            result.put(childEle.getNodeName(), newValue);
          }  
      } 
    } 
    return result;
  }
  
  public static String mapToXmlStr(Map<?, ?> data) {
    return toStr(mapToXml(data, "xml"));
  }
  
  public static String mapToXmlStr(Map<?, ?> data, boolean omitXmlDeclaration) {
    return toStr(mapToXml(data, "xml"), "UTF-8", false, omitXmlDeclaration);
  }
  
  public static String mapToXmlStr(Map<?, ?> data, String rootName) {
    return toStr(mapToXml(data, rootName));
  }
  
  public static String mapToXmlStr(Map<?, ?> data, String rootName, String namespace) {
    return toStr(mapToXml(data, rootName, namespace));
  }
  
  public static String mapToXmlStr(Map<?, ?> data, String rootName, String namespace, boolean omitXmlDeclaration) {
    return toStr(mapToXml(data, rootName, namespace), "UTF-8", false, omitXmlDeclaration);
  }
  
  public static String mapToXmlStr(Map<?, ?> data, String rootName, String namespace, boolean isPretty, boolean omitXmlDeclaration) {
    return toStr(mapToXml(data, rootName, namespace), "UTF-8", isPretty, omitXmlDeclaration);
  }
  
  public static String mapToXmlStr(Map<?, ?> data, String rootName, String namespace, String charset, boolean isPretty, boolean omitXmlDeclaration) {
    return toStr(mapToXml(data, rootName, namespace), charset, isPretty, omitXmlDeclaration);
  }
  
  public static Document mapToXml(Map<?, ?> data, String rootName) {
    return mapToXml(data, rootName, null);
  }
  
  public static Document mapToXml(Map<?, ?> data, String rootName, String namespace) {
    Document doc = createXml();
    Element root = appendChild(doc, rootName, namespace);
    appendMap(doc, root, data);
    return doc;
  }
  
  public static Document beanToXml(Object bean) {
    return beanToXml(bean, null);
  }
  
  public static Document beanToXml(Object bean, String namespace) {
    return beanToXml(bean, namespace, false);
  }
  
  public static Document beanToXml(Object bean, String namespace, boolean ignoreNull) {
    if (null == bean)
      return null; 
    return mapToXml(BeanUtil.beanToMap(bean, false, ignoreNull), bean
        .getClass().getSimpleName(), namespace);
  }
  
  public static boolean isElement(Node node) {
    return (null != node && 1 == node.getNodeType());
  }
  
  public static Element appendChild(Node node, String tagName) {
    return appendChild(node, tagName, null);
  }
  
  public static Element appendChild(Node node, String tagName, String namespace) {
    Document doc = getOwnerDocument(node);
    Element child = (null == namespace) ? doc.createElement(tagName) : doc.createElementNS(namespace, tagName);
    node.appendChild(child);
    return child;
  }
  
  public static Node appendText(Node node, CharSequence text) {
    return appendText(getOwnerDocument(node), node, text);
  }
  
  public static void append(Node node, Object data) {
    append(getOwnerDocument(node), node, data);
  }
  
  private static void append(Document doc, Node node, Object data) {
    if (data instanceof Map) {
      appendMap(doc, node, (Map)data);
    } else if (data instanceof Iterator) {
      appendIterator(doc, node, (Iterator)data);
    } else if (data instanceof Iterable) {
      appendIterator(doc, node, ((Iterable)data).iterator());
    } else {
      appendText(doc, node, data.toString());
    } 
  }
  
  private static void appendMap(Document doc, Node node, Map data) {
    data.forEach((key, value) -> {
          if (null != key) {
            Element child = appendChild(node, key.toString());
            if (null != value)
              append(doc, child, value); 
          } 
        });
  }
  
  private static void appendIterator(Document doc, Node node, Iterator data) {
    Node parentNode = node.getParentNode();
    boolean isFirst = true;
    while (data.hasNext()) {
      Object eleData = data.next();
      if (isFirst) {
        append(doc, node, eleData);
        isFirst = false;
        continue;
      } 
      Node cloneNode = node.cloneNode(false);
      parentNode.appendChild(cloneNode);
      append(doc, cloneNode, eleData);
    } 
  }
  
  private static Node appendText(Document doc, Node node, CharSequence text) {
    return node.appendChild(doc.createTextNode(StrUtil.str(text)));
  }
  
  private static DocumentBuilderFactory disableXXE(DocumentBuilderFactory dbf) {
    try {
      String feature = "http://apache.org/xml/features/disallow-doctype-decl";
      dbf.setFeature(feature, true);
      feature = "http://xml.org/sax/features/external-general-entities";
      dbf.setFeature(feature, false);
      feature = "http://xml.org/sax/features/external-parameter-entities";
      dbf.setFeature(feature, false);
      feature = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
      dbf.setFeature(feature, false);
      dbf.setXIncludeAware(false);
      dbf.setExpandEntityReferences(false);
    } catch (ParserConfigurationException parserConfigurationException) {}
    return dbf;
  }
  
  public static class UniversalNamespaceCache implements NamespaceContext {
    private static final String DEFAULT_NS = "DEFAULT";
    
    private final BiMap<String, String> prefixUri = new BiMap(new HashMap<>());
    
    public UniversalNamespaceCache(Node node, boolean toplevelOnly) {
      examineNode(node.getFirstChild(), toplevelOnly);
    }
    
    private void examineNode(Node node, boolean attributesOnly) {
      NamedNodeMap attributes = node.getAttributes();
      if (null != attributes) {
        int length = attributes.getLength();
        for (int i = 0; i < length; i++) {
          Node attribute = attributes.item(i);
          storeAttribute(attribute);
        } 
      } 
      if (false == attributesOnly) {
        NodeList childNodes = node.getChildNodes();
        if (null != childNodes) {
          int childLength = childNodes.getLength();
          for (int i = 0; i < childLength; i++) {
            Node item = childNodes.item(i);
            if (item.getNodeType() == 1)
              examineNode(item, false); 
          } 
        } 
      } 
    }
    
    private void storeAttribute(Node attribute) {
      if (null == attribute)
        return; 
      if ("http://www.w3.org/2000/xmlns/".equals(attribute.getNamespaceURI()))
        if ("xmlns".equals(attribute.getNodeName())) {
          this.prefixUri.put("DEFAULT", attribute.getNodeValue());
        } else {
          this.prefixUri.put(attribute.getLocalName(), attribute.getNodeValue());
        }  
    }
    
    public String getNamespaceURI(String prefix) {
      if (prefix == null || "".equals(prefix))
        return (String)this.prefixUri.get("DEFAULT"); 
      return (String)this.prefixUri.get(prefix);
    }
    
    public String getPrefix(String namespaceURI) {
      return (String)this.prefixUri.getInverse().get(namespaceURI);
    }
    
    public Iterator<String> getPrefixes(String namespaceURI) {
      return null;
    }
  }
}
