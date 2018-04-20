package cbox.assignments.qac;

// Simplifies the process of building SQL statements.
class SQLBuilder {
    private StringBuilder query = new StringBuilder();
    @Override
    public String toString() {
        return query.toString();
    }
    // Convenience.
    public static String sqlStr(String str) {
        return "\"" + str + "\"";
    }
    // CRUD: Create, read, update, delete.
    public SQLBuilder insert(String table, String[] columns, String[] values) {
        query.append("INSERT INTO ").append(table).append('(');
        for(String col: columns) {
            query.append(col).append(", ");
        }
        query.delete(query.length()-2, query.length());
        query.append(") ").append("VALUES(");
        for(String val: values) {
            query.append(val).append(", ");
        }
        query.delete(query.length()-2, query.length());
        query.append(')');

        return this;
    }
    // Query.
    public SQLBuilder select(String[] select, String from) {
        query.append("SELECT ");
        for (String cols: select) {
            query.append(cols).append(", ");
        }
        int len = query.length();
        query.replace(len-2, len, " ");
        query.append("FROM ").append(from).append(" ");
        return this;
    }
    public SQLBuilder select(String select, String from) {
        select(new String[]{select}, from);
        return this;
    }
    public SQLBuilder join(String join, String on1, String on2) {
        query.append("INNER JOIN ").append(join).append(" ");
        query.append("ON ").append(on1).append(" = ").append(on2).append(" ");
        return this;
    }
}
