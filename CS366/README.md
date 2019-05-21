# Introduction
## Definitions
* XML: Extensible Markup Language
* HTML: HyperText Markup Language
* SGML: Standard Generalized Markup Language
* XML and HTML are subsets of SGML.
* Element: Named key in XML. 
  * Ex: `<person></person>`
* Attribute: Extends data related to element. 
  * Ex: `<person title="mr"></person>`
* Content: Value associated with element.
  * Ex: `<person>Kyle</person>`

## Syntax Rules
1. Attribute Values must be quoted
  * Valid: `<person title="mr"></person>`
  * Invalid: `<person title=mr></person>`
2. Tags must be closed or self closing
  * Valid: `<person></person>`
  * Valid: `<person />`
  * Invalid: `<person>`
3. Element must be nested properly
  * Valid: `<person><name></name></person>`
  * Invalid: `<person><name></person></name>`

# Well-Formed
## Character Sets
* UTF: Unicode Transfer Formats
* UTF-8 is a 7-bit ASCII character
* UTF-16 is a 16-bit ASCII character

## XML Declaration
```XML
<!-- Example -->
<?xml version="1.0" encoding="UTF-8" standalong="yes"?>
```
* Version: indicates document is using XML V1.0
* Encoding: indicates the type of encoding
* Standalone: For use when there is a Document Type Definition

## Naming Rules
1. Names must start with an underscore (\_) or any upper or lowercase letter.
  * Valid: `<person> or <_person> or <Person>`
2. Characters after the first can also be a dash (-) or digit
  * Valid: `<person-adult> and <person1>` 
3. Colon (:) is also allowed but only for XML namespaces. 
  * Valid: `<m:person>`
4. Space ( ) and puntuation (, ! ?) are forbidden
  * Invalid: `<person!>`
  
**All XML documents can have exactly one element called the root element.**

## Attribute Rules
1. Attribute values must be in quotes: ' or "
  * Valid: `<person age='23'> or <person age="23">`
2. You can only have one attribute of a given name in any tag.
  * Invalid: `<person age="23" age="32"`
3. Newlines in attributes are turned into spaces.
4. (<) and (&) are forbidden characters.

### Element vs Attribute
Rule of Thumb: Use attributes for computer-readable strings, such as, studentID. 
Use elements for human-readable text such as a person's name. 

## Entities
There are five built-in XML entities:
1. `&amp; = (&)`
2. `&apos; = (')`
3. `&quot; = (")`
4. `&lt; = (<)`
5. `&gt; = (>)`

```xml
<!-- Entities can be used to insert characters or strings: -->
<person>Kyle is &apos;great&apos;</person>
Output: Kyle is 'great'
```
## CDATA
CDATA stands for Character Data.
CDATA defines a section of XML that should not be parsed.  

```XML
<!-- Example -->
<code><![CDATA[if (a[i] > 5 && j[a[i]] > 7) {document.write("you lost a sock!");}]]></code>
```
Characters within CDATA do not need to be escaped.

# Namespaces
* Definition: A namespace is a way to group elements and attributes together under a common label.

## Namespace Rules
1. The name must be unique.
2. The name should be in the space you own.
3. The name should be descriptive.
4. The name must be a URI or URN.
5. Namespaces are case sensitive.

```XML
<!-- Example -->
<person xmlns="https://mywsu.winona.edu/search/Pages/people.aspx"><person>
```

## Default Namespace
When using `xmlns=""` this is defining the default namespace for all elements inside the one with the namespace declaration.

## Namespace Prefixes
When using multiple namespaces in the same document use prefixes to explicity define namespace for each element, otherwise the parser will use the default namespace if avaiable. 

```XML
<!-- Example -->
<d:directory
  xmlns:d="https://mywsu.winona.edu/search/Pages/people.aspx"
  xmlns:p="https://mywsu.winona.edu/search/Pages/peopleresults.aspx?k=chi">
 <p:person>chi</person>
</directory>
```
## Namespaces on attributes
Attributes are in no namespace unless they are explicitly prefixed. 

# Document Type Definitions (DTD)
Definition: DTD defines the structure an XML document needs to adhear to.

## Linking
* DTD defines formal grammar that gets validated by the XML parser.
* XML document needs to start with a DOCTYPE declaration to use DTD. 
* Inline DTD can be defined
  * Example: `<!DOCTYPE person>` 
* System DTD can be defined
  * Example: `<!DOCTYPE person SYSTEM "person.dtd">`
* Public DTD can be defined
  * Example: `<!DOCTYPE person PUBLIC "-//WSU//DTD for contacts//EN" "contacts.dtd">`
  * When using a public DTD you must also define a system dtd as backup.
 
## Element Syntax
1. Elements - Defines element name, and sub-elements name and order.
  * Example: `<!Element person (first, last)>`
2. 
TODO finish element syntax
 
 
## Entities
### Within XML Document:
* General Entity (GE) Reference 
  * Example `<!ENTITY person-first "Kyle">`
  * Could refer to entity declared within XML or within DTD.
* Parameter Entity (PE) Reference
  * Excample `<!ENTITY % people SYSTEM "people.txt">`
  * Could refer to parameter declared within DTD or separate external file.
 
### Within DTD Document:
* Internal GE Declaration
  * -> Which is then referenced by XML file
* External GE Declaration 
  * -> Points to an external file, and is then referenced by XML file
* Internal PE Declaration
  * -> Which is then referenced by DTD file
* External PE Declaration
  * -> Points to an external file, and is then referenced by DTD file
 
Entities are direct string substitution. 

# Schema Introduction
## Definitions and General Info
* XML Schema is XML itself
* Developed by W3C
* Root element must be schema
* File format: .xsd

## Writing a Schema
* Schema Tag (required): `<schema></schema>`
* Default Namespace (required - standard): `xmlns:xs="http://www.w3.org/2001/XMLSchema"`
* Target Namespace (optional): `targetNamespace="https://cs.winona.edu/kyle" xmlns:target="https://cs.winona.edu/kyle"`
* Element Form (required): `elementFormDefault="qualified"`
  * qualified - if elements are associated with a target namespace.
  * unqualified - if elements are not associated with a target namespace.

```XML
<!-- Example: schema.xsd -->
<xs:schema
  xmlns:xs="http://www.w3.org/2001/XMLSchema"
  xmlns:target="https://cs.winona.edu/kyle"
  targetNamespace="https://cs.winona.edu/kyle"
  elementFormDefault="qualified">
  ...
</xs:schema>
```

## Link Schema to XML Document
* Default Namespace (optional): Same as target namespace in schema. `xmlns="https://cs.winona.edu/kyle"`
* XML Schema Instance (required - standard): `xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"`
* Schema Location (required): `xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"` 

```XML
<!-- Example: person.xml -->
<person
  xmlns="https://cs.winona.edu/kyle"
  xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="https://cs.winona.edu/kyle schema.xsd">
  ...
</person>
```

## Element Declaration
* The only required fields is name and type

```XML
<!-- Example. person.xds -->
<element name="name" type="type" id=ID ref="global element declaration" form="qualified|unqualified" minOccurs="min" maxOccurs="max" default="default value"  fixed="fixed value" /> 
```

