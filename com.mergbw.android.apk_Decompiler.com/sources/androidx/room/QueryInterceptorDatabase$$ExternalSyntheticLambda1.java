package androidx.room;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class QueryInterceptorDatabase$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ QueryInterceptorDatabase f$0;

    public /* synthetic */ QueryInterceptorDatabase$$ExternalSyntheticLambda1(QueryInterceptorDatabase queryInterceptorDatabase) {
        this.f$0 = queryInterceptorDatabase;
    }

    public final void run() {
        QueryInterceptorDatabase.beginTransactionWithListenerNonExclusive$lambda$3(this.f$0);
    }
}
