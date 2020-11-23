# LuceneDemo
Lucene Examples

1. Indexer (ic) - Basic index creation.
2. Index Tuning (itc) - Tuned indexing.
3. File Indexer (fic) - Add a new file to index.
4. Term Query (tqc) - Make a search in index by term.
5. Search (sc) - Make a search by word.
6. Phrase Query (pqc) - Make a phrase query.
7. Prefix Query (prqc) - Search with a prefix query.
8. Span Term Query (stqc) - Span term query search.
9. Span Near Query (snqc) - Span near search query. 
10. Wildcard Query (wqc) - Search query containing wildcard(e.g ?ope*).
11. Boolean Query (bqc) - Search query with boolean clauses.
12. Multifield Query (mqc) - Search query for multiple fields.
13. Complex Phrase Query (cpqc) - Search query for complex phrases (e.g with "when, while")
14. Sorting (soc) - Sort search query results.
15. Analyzer (ac) - Usage of different analyzers (standard,whitespace,simple,stop).
16. Chinese (cc) - Usage of analyzers against chinese characters.
17. Custom Hit Collector (chc) - Custom collector for search term hits.
18. Explanator (ec) - Show different parameters of search results(e.g wieght,score etc.) 
and the way how are they computed
19. Like this (ltc) - Find similar documents    

##Build and run

```yaml
mvn clean install

java -jar <jar-location>/lucene.jar <arg>
```

## Notes
- Arguments to run are in ()
- Change index dir to your own in index util.
- For File Indexer create a new file.
- Search query demos require running indexer
