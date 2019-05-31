# XPath Exercise
From file: book.xml

1. All Chapter Elements 
  * `/Book/Chapter`
2. All attribute number
  * `/Book/Chapter/@number`
3. Chapter with attribute number=“3”
  * `/Book/Chapter[@number=3]`
4. 3rd Chapter of Book
  * `/Book/Chapter[3]`
5. Last Chapter of Book
  * `/Book/Chapter[last()]`
6. First 2 Chapter of Book
  * `/Book/Chapter[position()<3]`
7. Content of 2nd Chapter of Book
  * `/Book/Chapter[2]/text()`
8. Attribute number of 2nd Chapter of Book
  * `/Book/Chapter[2]/@number`
9. Attribute number’s value of 2nd Chapter of Book
  * `string(/Book/Chapter[2]/@number)`
10. 2nd and 4th Chapter of Book
  * `/Book/Chapter[2] | /Book/Chapter[4]` or `/Book/Chapter[position()=2 or position()=4]`
11. elements with 5 child elements
  * `//*[count(*)=5]`
12. string value of Book
  * `/Book/string()`
13 What does /Book/text() return?
  *
+
```text
Text='
 '

Text='
 '

Text='
 '

Text='
 '

Text='
 '

Text='
'

```

