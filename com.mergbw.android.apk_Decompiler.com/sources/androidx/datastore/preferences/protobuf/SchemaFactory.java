package androidx.datastore.preferences.protobuf;

interface SchemaFactory {
    <T> Schema<T> createSchema(Class<T> cls);
}
