# LuceneDemo
Lucene Examples

1. Indexer (ic) - Basic index creation
2. Index Tuning (itc) - Tuned indexing
3. File Indexer (fic) - Add a new file to index.
4. Term Query (tqc) - Make a search in index by term.
5. Search (sc) - Make a search by word.
6. Phrase Query (pqc) - Make a phrase query.
7. Prefix Query (prqc) - Search with a prefix query.



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
