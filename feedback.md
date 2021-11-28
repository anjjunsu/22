# mp2 Feedback

## Grade: B

| Compilation | Timeout | Duration |
|:-----------:|:-------:|:--------:|
||NoNoNoYes|627.98|

## Comments
The extra constructor as mentioned in campuswire #1182 was not added in. RI/AFs look great on all the classes, and specs on public methods are also good. Very good use of helper methods, the code is very readable.
## Test Results 
### cpen221.mp2.Task1DWTests
| Test Status | Count |
| ----------- | ----- |
|tests|9|
|skipped|0|
|failures|1|
|errors|0|
#### Failed Tests
1. `testRandomSpacesInFile() (java.lang.NumberFormatException: For input string: "")`
### cpen221.mp2.Task1UDWTests
| Test Status | Count |
| ----------- | ----- |
|tests|10|
|skipped|0|
|failures|1|
|errors|0|
#### Failed Tests
1. `testRandomSpacesInFile() (java.lang.NumberFormatException: For input string: "")`
### cpen221.mp2.Task2DWTests
| Test Status | Count |
| ----------- | ----- |
|tests|15|
|skipped|0|
|failures|0|
|errors|0|
### cpen221.mp2.Task2UDWTests
| Test Status | Count |
| ----------- | ----- |
|tests|11|
|skipped|0|
|failures|0|
|errors|0|
### cpen221.mp2.Task3DWTests
| Test Status | Count |
| ----------- | ----- |
|tests|12|
|skipped|0|
|failures|0|
|errors|0|
### cpen221.mp2.Task3UDWTests
| Test Status | Count |
| ----------- | ----- |
|tests|8|
|skipped|0|
|failures|0|
|errors|0|

## Code Quality
```
DWInteractionGraph.java:9:	The class 'DWInteractionGraph' has a total cyclomatic complexity of 86 (highest 9).
DWInteractionGraph.java:9:	This class has too many methods, consider refactoring it.
DWInteractionGraph.java:15:	Avoid using implementation types like 'HashMap'; use the interface instead
DWInteractionGraph.java:15:	Found non-transient, non-static member. Please mark as transient or provide accessors.
DWInteractionGraph.java:16:	Found non-transient, non-static member. Please mark as transient or provide accessors.
DWInteractionGraph.java:16:	Private field 'emailData' could be made final; it is only initialized in the declaration or constructor.
DWInteractionGraph.java:17:	Found non-transient, non-static member. Please mark as transient or provide accessors.
DWInteractionGraph.java:18:	Found non-transient, non-static member. Please mark as transient or provide accessors.
DWInteractionGraph.java:83:	Consider using varargs for methods or constructors which take an array the last parameter.
DWInteractionGraph.java:151:	Found 'DD'-anomaly for variable 'weight' (lines '151'-'154').
DWInteractionGraph.java:154:	Found 'DD'-anomaly for variable 'weight' (lines '154'-'154').
DWInteractionGraph.java:175:	Found 'DD'-anomaly for variable 'tempList' (lines '175'-'175').
DWInteractionGraph.java:175:	Found 'DU'-anomaly for variable 'tempList' (lines '175'-'191').
DWInteractionGraph.java:176:	Found 'DD'-anomaly for variable 'weight' (lines '176'-'179').
DWInteractionGraph.java:179:	Found 'DD'-anomaly for variable 'weight' (lines '179'-'179').
DWInteractionGraph.java:201:	Ensure that resources like this FileReader object are closed after use
DWInteractionGraph.java:209:	System.out.println is used
DWInteractionGraph.java:248:	Consider using varargs for methods or constructors which take an array the last parameter.
DWInteractionGraph.java:249:	Found 'DU'-anomaly for variable 'START_TIME_INDEX' (lines '249'-'276').
DWInteractionGraph.java:250:	Found 'DU'-anomaly for variable 'END_TIME_INDEX' (lines '250'-'276').
DWInteractionGraph.java:251:	Found 'DD'-anomaly for variable 'report' (lines '251'-'272').
DWInteractionGraph.java:252:	Found 'DD'-anomaly for variable 'numEmailTransaction' (lines '252'-'263').
DWInteractionGraph.java:263:	Found 'DD'-anomaly for variable 'numEmailTransaction' (lines '263'-'263').
DWInteractionGraph.java:272:	Found 'DD'-anomaly for variable 'report' (lines '272'-'273').
DWInteractionGraph.java:273:	Found 'DD'-anomaly for variable 'report' (lines '273'-'274').
DWInteractionGraph.java:289:	Found 'DD'-anomaly for variable 'report' (lines '289'-'316').
DWInteractionGraph.java:289:	Found 'DU'-anomaly for variable 'report' (lines '289'-'320').
DWInteractionGraph.java:290:	Found 'DU'-anomaly for variable 'numSent' (lines '290'-'320').
DWInteractionGraph.java:291:	Found 'DU'-anomaly for variable 'numReceive' (lines '291'-'320').
DWInteractionGraph.java:293:	Found 'DU'-anomaly for variable 'uniqueUserSet' (lines '293'-'320').
DWInteractionGraph.java:316:	Found 'DD'-anomaly for variable 'report' (lines '316'-'317').
DWInteractionGraph.java:317:	Found 'DD'-anomaly for variable 'report' (lines '317'-'318').
DWInteractionGraph.java:335:	Avoid unused local variables such as 'validSendRank'.
DWInteractionGraph.java:335:	Found 'DU'-anomaly for variable 'validSendRank' (lines '335'-'380').
DWInteractionGraph.java:336:	Avoid unused local variables such as 'validReceiveRank'.
DWInteractionGraph.java:336:	Found 'DU'-anomaly for variable 'validReceiveRank' (lines '336'-'380').
DWInteractionGraph.java:337:	Found 'DD'-anomaly for variable 'NthMostActiveUser' (lines '337'-'370').
DWInteractionGraph.java:337:	Found 'DD'-anomaly for variable 'NthMostActiveUser' (lines '337'-'376').
DWInteractionGraph.java:337:	Found 'DU'-anomaly for variable 'NthMostActiveUser' (lines '337'-'380').
DWInteractionGraph.java:357:	Found 'DU'-anomaly for variable 'sendRanking' (lines '357'-'380').
DWInteractionGraph.java:358:	Potential violation of Law of Demeter (method chain calls)
DWInteractionGraph.java:358:	Potential violation of Law of Demeter (method chain calls)
DWInteractionGraph.java:358:	Potential violation of Law of Demeter (method chain calls)
DWInteractionGraph.java:358:	Potential violation of Law of Demeter (object not created locally)
DWInteractionGraph.java:359:	Potential violation of Law of Demeter (method chain calls)
DWInteractionGraph.java:359:	Potential violation of Law of Demeter (method chain calls)
DWInteractionGraph.java:361:	Found 'DU'-anomaly for variable 'receiveRanking' (lines '361'-'380').
DWInteractionGraph.java:362:	Potential violation of Law of Demeter (method chain calls)
DWInteractionGraph.java:362:	Potential violation of Law of Demeter (method chain calls)
DWInteractionGraph.java:362:	Potential violation of Law of Demeter (method chain calls)
DWInteractionGraph.java:362:	Potential violation of Law of Demeter (object not created locally)
DWInteractionGraph.java:363:	Potential violation of Law of Demeter (method chain calls)
DWInteractionGraph.java:363:	Potential violation of Law of Demeter (method chain calls)
DWInteractionGraph.java:370:	Found 'DD'-anomaly for variable 'NthMostActiveUser' (lines '370'-'376').
DWInteractionGraph.java:370:	Found 'DU'-anomaly for variable 'NthMostActiveUser' (lines '370'-'380').
DWInteractionGraph.java:370:	Potential violation of Law of Demeter (method chain calls)
DWInteractionGraph.java:376:	Potential violation of Law of Demeter (method chain calls)
DWInteractionGraph.java:393:	Found 'DU'-anomaly for variable 'queue' (lines '393'-'424').
DWInteractionGraph.java:394:	Found 'DU'-anomaly for variable 'isVisited' (lines '394'-'424').
DWInteractionGraph.java:395:	Found 'DU'-anomaly for variable 'root' (lines '395'-'424').
DWInteractionGraph.java:396:	Found 'DD'-anomaly for variable 'found' (lines '396'-'409').
DWInteractionGraph.java:396:	Found 'DU'-anomaly for variable 'found' (lines '396'-'424').
DWInteractionGraph.java:409:	Found 'DD'-anomaly for variable 'found' (lines '409'-'409').
DWInteractionGraph.java:437:	Found 'DU'-anomaly for variable 'isVisited' (lines '437'-'451').
DWInteractionGraph.java:438:	Found 'DD'-anomaly for variable 'found' (lines '438'-'444').
DWInteractionGraph.java:438:	Found 'DU'-anomaly for variable 'found' (lines '438'-'451').
DWInteractionGraph.java:438:	The initializer for variable 'found' is never used (overwritten on line 444)
DWInteractionGraph.java:462:	Found 'DD'-anomaly for variable 'found' (lines '462'-'465').
DWInteractionGraph.java:462:	Found 'DD'-anomaly for variable 'found' (lines '462'-'471').
DWInteractionGraph.java:464:	Use equals() to compare object references.
DWInteractionGraph.java:465:	Found 'DD'-anomaly for variable 'found' (lines '465'-'471').
DWInteractionGraph.java:471:	Found 'DD'-anomaly for variable 'found' (lines '471'-'471').
DWInteractionGraph.java:489:	Found 'DU'-anomaly for variable 'totalSeconds' (lines '489'-'528').
DWInteractionGraph.java:493:	Potential violation of Law of Demeter (method chain calls)
DWInteractionGraph.java:501:	Found 'DD'-anomaly for variable 'count' (lines '501'-'501').
DWInteractionGraph.java:501:	Found 'DD'-anomaly for variable 'count' (lines '501'-'520').
DWInteractionGraph.java:501:	Found 'DU'-anomaly for variable 'count' (lines '501'-'528').
DWInteractionGraph.java:501:	The initializer for variable 'count' is never used (overwritten on line 520)
DWInteractionGraph.java:513:	Potential violation of Law of Demeter (object not created locally)
DWInteractionGraph.java:542:	Found 'DU'-anomaly for variable 'nodeVisited' (lines '542'-'566').
Edge.java:4:	Found non-transient, non-static member. Please mark as transient or provide accessors.
Edge.java:4:	Private field 'sender' could be made final; it is only initialized in the declaration or constructor.
Edge.java:5:	Found non-transient, non-static member. Please mark as transient or provide accessors.
Edge.java:5:	Private field 'receiver' could be made final; it is only initialized in the declaration or constructor.
Edge.java:6:	Found non-transient, non-static member. Please mark as transient or provide accessors.
Edge.java:6:	Private field 'weight' could be made final; it is only initialized in the declaration or constructor.
Edge.java:39:	System.out.println is used
Element.java:4:	Found non-transient, non-static member. Please mark as transient or provide accessors.
Element.java:4:	Private field 'index' could be made final; it is only initialized in the declaration or constructor.
Element.java:5:	Found non-transient, non-static member. Please mark as transient or provide accessors.
Element.java:5:	Private field 'value' could be made final; it is only initialized in the declaration or constructor.
Element.java:52:	System.out.println is used
UDWInteractionGraph.java:7:	Avoid unused imports such as 'java.util.Arrays'
UDWInteractionGraph.java:17:	Possible God Class (WMC=74, ATFD=70, TCC=26.316%)
UDWInteractionGraph.java:17:	The class 'UDWInteractionGraph' has a Modified Cyclomatic Complexity of 3 (Highest = 10).
UDWInteractionGraph.java:17:	The class 'UDWInteractionGraph' has a Standard Cyclomatic Complexity of 3 (Highest = 10).
UDWInteractionGraph.java:17:	This class has too many methods, consider refactoring it.
UDWInteractionGraph.java:24:	Found non-transient, non-static member. Please mark as transient or provide accessors.
UDWInteractionGraph.java:25:	Found non-transient, non-static member. Please mark as transient or provide accessors.
UDWInteractionGraph.java:26:	Found non-transient, non-static member. Please mark as transient or provide accessors.
UDWInteractionGraph.java:27:	Found non-transient, non-static member. Please mark as transient or provide accessors.
UDWInteractionGraph.java:28:	Found non-transient, non-static member. Please mark as transient or provide accessors.
UDWInteractionGraph.java:28:	Private field 'UDWIG' could be made final; it is only initialized in the declaration or constructor.
UDWInteractionGraph.java:76:	Potential violation of Law of Demeter (method chain calls)
UDWInteractionGraph.java:123:	This for loop can be replaced by a foreach loop
UDWInteractionGraph.java:126:	Found 'DD'-anomaly for variable 'sentItself' (lines '126'-'137').
UDWInteractionGraph.java:129:	This for loop can be replaced by a foreach loop
UDWInteractionGraph.java:130:	Potential violation of Law of Demeter (method chain calls)
UDWInteractionGraph.java:131:	Potential violation of Law of Demeter (method chain calls)
UDWInteractionGraph.java:132:	Potential violation of Law of Demeter (method chain calls)
UDWInteractionGraph.java:133:	Potential violation of Law of Demeter (method chain calls)
UDWInteractionGraph.java:135:	Potential violation of Law of Demeter (method chain calls)
UDWInteractionGraph.java:136:	Potential violation of Law of Demeter (method chain calls)
UDWInteractionGraph.java:137:	Found 'DD'-anomaly for variable 'sentItself' (lines '137'-'137').
UDWInteractionGraph.java:141:	Avoid using Literals in Conditional Statements
UDWInteractionGraph.java:160:	Found 'DU'-anomaly for variable 'userListToExclude' (lines '160'-'181').
UDWInteractionGraph.java:164:	This for loop can be replaced by a foreach loop
UDWInteractionGraph.java:167:	Potential violation of Law of Demeter (method chain calls)
UDWInteractionGraph.java:168:	Potential violation of Law of Demeter (method chain calls)
UDWInteractionGraph.java:169:	Potential violation of Law of Demeter (method chain calls)
UDWInteractionGraph.java:170:	Potential violation of Law of Demeter (method chain calls)
UDWInteractionGraph.java:172:	Potential violation of Law of Demeter (method chain calls)
UDWInteractionGraph.java:173:	Potential violation of Law of Demeter (method chain calls)
UDWInteractionGraph.java:202:	Found 'DD'-anomaly for variable 'weight' (lines '202'-'204').
UDWInteractionGraph.java:203:	This for loop can be replaced by a foreach loop
UDWInteractionGraph.java:204:	Found 'DD'-anomaly for variable 'weight' (lines '204'-'204').
UDWInteractionGraph.java:221:	Ensure that resources like this FileReader object are closed after use
UDWInteractionGraph.java:229:	System.out.println is used
UDWInteractionGraph.java:265:	Consider using varargs for methods or constructors which take an array the last parameter.
UDWInteractionGraph.java:269:	This for loop can be replaced by a foreach loop
UDWInteractionGraph.java:362:	Consider using varargs for methods or constructors which take an array the last parameter.
UDWInteractionGraph.java:366:	Found 'DD'-anomaly for variable 'reportActivity' (lines '366'-'377').
UDWInteractionGraph.java:370:	This for loop can be replaced by a foreach loop
UDWInteractionGraph.java:371:	Potential violation of Law of Demeter (method chain calls)
UDWInteractionGraph.java:377:	Found 'DD'-anomaly for variable 'reportActivity' (lines '377'-'382').
UDWInteractionGraph.java:377:	Potential violation of Law of Demeter (method chain calls)
UDWInteractionGraph.java:402:	Found 'DD'-anomaly for variable 'reportOnUse' (lines '402'-'426').
UDWInteractionGraph.java:403:	Found 'DD'-anomaly for variable 'numberOfEmails' (lines '403'-'416').
UDWInteractionGraph.java:404:	Found 'DD'-anomaly for variable 'uniqueUserInteractedWith' (lines '404'-'419').
UDWInteractionGraph.java:405:	Found 'DU'-anomaly for variable 'notUniqueInteractions' (lines '405'-'429').
UDWInteractionGraph.java:416:	Found 'DD'-anomaly for variable 'numberOfEmails' (lines '416'-'416').
UDWInteractionGraph.java:419:	Found 'DD'-anomaly for variable 'uniqueUserInteractedWith' (lines '419'-'419').
UDWInteractionGraph.java:426:	Found 'DD'-anomaly for variable 'reportOnUse' (lines '426'-'427').
UDWInteractionGraph.java:437:	The method 'NthMostActiveUser' has a Modified Cyclomatic Complexity of 10.
UDWInteractionGraph.java:437:	The method 'NthMostActiveUser' has a Standard Cyclomatic Complexity of 10.
UDWInteractionGraph.java:437:	The method 'NthMostActiveUser(int)' has a cyclomatic complexity of 11.
UDWInteractionGraph.java:439:	Found 'DD'-anomaly for variable 'userTotalEmailList' (lines '439'-'458').
UDWInteractionGraph.java:440:	Found 'DD'-anomaly for variable 'eachUserList' (lines '440'-'459').
UDWInteractionGraph.java:445:	This for loop can be replaced by a foreach loop
UDWInteractionGraph.java:446:	Potential violation of Law of Demeter (method chain calls)
UDWInteractionGraph.java:449:	Potential violation of Law of Demeter (method chain calls)
UDWInteractionGraph.java:451:	Potential violation of Law of Demeter (method chain calls)
UDWInteractionGraph.java:453:	Potential violation of Law of Demeter (method chain calls)
UDWInteractionGraph.java:458:	Found 'DD'-anomaly for variable 'userTotalEmailList' (lines '458'-'458').
UDWInteractionGraph.java:458:	Found 'DU'-anomaly for variable 'userTotalEmailList' (lines '458'-'475').
UDWInteractionGraph.java:459:	Found 'DD'-anomaly for variable 'eachUserList' (lines '459'-'459').
UDWInteractionGraph.java:484:	Consider using varargs for methods or constructors which take an array the last parameter.
UDWInteractionGraph.java:521:	Found 'DD'-anomaly for variable 'componentList' (lines '521'-'525').
UDWInteractionGraph.java:521:	The initializer for variable 'componentList' is never used (overwritten on line 525)
UDWInteractionGraph.java:526:	This for loop can be replaced by a foreach loop
UDWInteractionGraph.java:527:	Potential violation of Law of Demeter (method chain calls)
UDWInteractionGraph.java:527:	Potential violation of Law of Demeter (method chain calls)
UDWInteractionGraph.java:543:	This for loop can be replaced by a foreach loop
UDWInteractionGraph.java:564:	Potential violation of Law of Demeter (method chain calls)
UDWInteractionGraph.java:565:	Potential violation of Law of Demeter (method chain calls)
UDWInteractionGraph.java:566:	Potential violation of Law of Demeter (method chain calls)
UDWInteractionGraph.java:567:	Potential violation of Law of Demeter (method chain calls)
LoosePackageCoupling	-	No packages or classes specified
```

## Test Coverage
### UDWInteractionGraph
| Metric | Coverage |
| ------ | -------- |
|LINE_MISSED|228|
|LINE_COVERED|0|
|BRANCH_MISSED|120|
|BRANCH_COVERED|0|
### DWInteractionGraph
| Metric | Coverage |
| ------ | -------- |
|LINE_MISSED|233|
|LINE_COVERED|38|
|BRANCH_MISSED|140|
|BRANCH_COVERED|18|

## Checkstyle Results
### `DWInteractionGraph.java`
| Line | Column | Message |
| ---- | ------ | ------- |
| 1 |  | `File does not end with a newline.` |
| 6 | 17 | `Using the '.*' form of import should be avoided - java.util.*.` |
| 52 |  | `Line is longer than 100 characters (found 108).` |
| 84 | 13 | `Name 'START_TIME_INDEX' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 85 | 13 | `Name 'END_TIME_INDEX' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 89 | 67 | `'&&' should be on a new line.` |
| 161 |  | `Line is longer than 100 characters (found 114).` |
| 163 |  | `Line is longer than 100 characters (found 109).` |
| 198 |  | `@return tag should be present and have description.` |
| 217 |  | `Line is longer than 100 characters (found 114).` |
| 235 | 35 | `Name 'getDWI_data' must match pattern '^[a-z][a-zA-Z0-9]*$'.` |
| 248 | 18 | `Name 'ReportActivityInTimeWindow' must match pattern '^[a-z][a-zA-Z0-9]*$'.` |
| 249 | 13 | `Name 'START_TIME_INDEX' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 250 | 13 | `Name 'END_TIME_INDEX' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 260 | 70 | `'&&' should be on a new line.` |
| 288 | 18 | `Name 'ReportOnUser' must match pattern '^[a-z][a-zA-Z0-9]*$'.` |
| 331 | 16 | `Name 'NthMostActiveUser' must match pattern '^[a-z][a-zA-Z0-9]*$'.` |
| 337 | 13 | `Name 'NthMostActiveUser' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 359 |  | `Line is longer than 100 characters (found 104).` |
| 363 |  | `Line is longer than 100 characters (found 104).` |
| 392 | 26 | `Name 'BFS' must match pattern '^[a-z][a-zA-Z0-9]*$'.` |
| 436 | 26 | `Name 'DFS' must match pattern '^[a-z][a-zA-Z0-9]*$'.` |
| 468 | 63 | `'&&' should be on a new line.` |
| 485 | 16 | `Name 'MaxBreachedUserCount' must match pattern '^[a-z][a-zA-Z0-9]*$'.` |
| 489 | 40 | `'3600' is a magic number.` |
| 538 | 17 | `Name 'BFS_for_MaxBreached' must match pattern '^[a-z][a-zA-Z0-9]*$'.` |
### `Edge.java`
| Line | Column | Message |
| ---- | ------ | ------- |
| 15 |  | `Line is longer than 100 characters (found 103).` |
| 36 |  | `Line is longer than 100 characters (found 101).` |
### `Element.java`
| Line | Column | Message |
| ---- | ------ | ------- |
### `SendOrReceive.java`
| Line | Column | Message |
| ---- | ------ | ------- |
### `UDWInteractionGraph.java`
| Line | Column | Message |
| ---- | ------ | ------- |
| 1 |  | `File does not end with a newline.` |
| 7 | 8 | `Unused import - java.util.Arrays.` |
| 46 |  | `Line is longer than 100 characters (found 117).` |
| 47 |  | `Line is longer than 100 characters (found 106).` |
| 90 | 33 | `Name 'getUDWI_data' must match pattern '^[a-z][a-zA-Z0-9]*$'.` |
| 130 | 71 | `'||' should be on a new line.` |
| 159 | 37 | `'emailWeightMap' hides a field.` |
| 197 | 52 | `'||' should be on a new line.` |
| 296 | 60 | `'||' should be on a new line.` |
| 324 | 22 | `Name 'IDSet' must match pattern '^([A-Z][0-9]*|[a-z][a-zA-Z0-9]*)$'.` |
| 362 | 18 | `Name 'ReportActivityInTimeWindow' must match pattern '^[a-z][a-zA-Z0-9]*$'.` |
| 401 | 18 | `Name 'ReportOnUser' must match pattern '^[a-z][a-zA-Z0-9]*$'.` |
| 417 | 68 | `'&&' should be on a new line.` |
| 437 | 16 | `Name 'NthMostActiveUser' must match pattern '^[a-z][a-zA-Z0-9]*$'.` |
| 468 | 59 | `'&&' should be on a new line.` |
| 501 | 16 | `Name 'NumberOfComponents' must match pattern '^[a-z][a-zA-Z0-9]*$'.` |
| 518 | 20 | `Name 'PathExists' must match pattern '^[a-z][a-zA-Z0-9]*$'.` |

