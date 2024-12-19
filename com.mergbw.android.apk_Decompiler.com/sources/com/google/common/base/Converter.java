package com.google.common.base;

import com.google.errorprone.annotations.CheckReturnValue;
import com.google.errorprone.annotations.concurrent.LazyInit;
import java.io.Serializable;
import java.util.Iterator;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
public abstract class Converter<A, B> implements Function<A, B> {
    private final boolean handleNullAutomatically;
    @CheckForNull
    @LazyInit
    private transient Converter<B, A> reverse;

    /* access modifiers changed from: protected */
    public abstract A doBackward(B b2);

    /* access modifiers changed from: protected */
    public abstract B doForward(A a2);

    protected Converter() {
        this(true);
    }

    Converter(boolean z) {
        this.handleNullAutomatically = z;
    }

    @CheckForNull
    public final B convert(@CheckForNull A a2) {
        return correctedDoForward(a2);
    }

    /* access modifiers changed from: package-private */
    @CheckForNull
    public B correctedDoForward(@CheckForNull A a2) {
        if (!this.handleNullAutomatically) {
            return unsafeDoForward(a2);
        }
        if (a2 == null) {
            return null;
        }
        return Preconditions.checkNotNull(doForward(a2));
    }

    /* access modifiers changed from: package-private */
    @CheckForNull
    public A correctedDoBackward(@CheckForNull B b2) {
        if (!this.handleNullAutomatically) {
            return unsafeDoBackward(b2);
        }
        if (b2 == null) {
            return null;
        }
        return Preconditions.checkNotNull(doBackward(b2));
    }

    @CheckForNull
    private B unsafeDoForward(@CheckForNull A a2) {
        return doForward(NullnessCasts.uncheckedCastNullableTToT(a2));
    }

    @CheckForNull
    private A unsafeDoBackward(@CheckForNull B b2) {
        return doBackward(NullnessCasts.uncheckedCastNullableTToT(b2));
    }

    public Iterable<B> convertAll(final Iterable<? extends A> iterable) {
        Preconditions.checkNotNull(iterable, "fromIterable");
        return new Iterable<B>() {
            public Iterator<B> iterator() {
                return new Iterator<B>() {
                    private final Iterator<? extends A> fromIterator;

                    {
                        this.fromIterator = iterable.iterator();
                    }

                    public boolean hasNext() {
                        return this.fromIterator.hasNext();
                    }

                    @CheckForNull
                    public B next() {
                        return Converter.this.convert(this.fromIterator.next());
                    }

                    public void remove() {
                        this.fromIterator.remove();
                    }
                };
            }
        };
    }

    @CheckReturnValue
    public Converter<B, A> reverse() {
        Converter<B, A> converter = this.reverse;
        if (converter != null) {
            return converter;
        }
        ReverseConverter reverseConverter = new ReverseConverter(this);
        this.reverse = reverseConverter;
        return reverseConverter;
    }

    private static final class ReverseConverter<A, B> extends Converter<B, A> implements Serializable {
        private static final long serialVersionUID = 0;
        final Converter<A, B> original;

        ReverseConverter(Converter<A, B> converter) {
            this.original = converter;
        }

        /* access modifiers changed from: protected */
        public A doForward(B b2) {
            throw new AssertionError();
        }

        /* access modifiers changed from: protected */
        public B doBackward(A a2) {
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        @CheckForNull
        public A correctedDoForward(@CheckForNull B b2) {
            return this.original.correctedDoBackward(b2);
        }

        /* access modifiers changed from: package-private */
        @CheckForNull
        public B correctedDoBackward(@CheckForNull A a2) {
            return this.original.correctedDoForward(a2);
        }

        public Converter<A, B> reverse() {
            return this.original;
        }

        public boolean equals(@CheckForNull Object obj) {
            if (obj instanceof ReverseConverter) {
                return this.original.equals(((ReverseConverter) obj).original);
            }
            return false;
        }

        public int hashCode() {
            return ~this.original.hashCode();
        }

        public String toString() {
            String valueOf = String.valueOf(this.original);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 10);
            sb.append(valueOf);
            sb.append(".reverse()");
            return sb.toString();
        }
    }

    public final <C> Converter<A, C> andThen(Converter<B, C> converter) {
        return doAndThen(converter);
    }

    /* access modifiers changed from: package-private */
    public <C> Converter<A, C> doAndThen(Converter<B, C> converter) {
        return new ConverterComposition(this, (Converter) Preconditions.checkNotNull(converter));
    }

    private static final class ConverterComposition<A, B, C> extends Converter<A, C> implements Serializable {
        private static final long serialVersionUID = 0;
        final Converter<A, B> first;
        final Converter<B, C> second;

        ConverterComposition(Converter<A, B> converter, Converter<B, C> converter2) {
            this.first = converter;
            this.second = converter2;
        }

        /* access modifiers changed from: protected */
        public C doForward(A a2) {
            throw new AssertionError();
        }

        /* access modifiers changed from: protected */
        public A doBackward(C c2) {
            throw new AssertionError();
        }

        /* access modifiers changed from: package-private */
        @CheckForNull
        public C correctedDoForward(@CheckForNull A a2) {
            return this.second.correctedDoForward(this.first.correctedDoForward(a2));
        }

        /* access modifiers changed from: package-private */
        @CheckForNull
        public A correctedDoBackward(@CheckForNull C c2) {
            return this.first.correctedDoBackward(this.second.correctedDoBackward(c2));
        }

        public boolean equals(@CheckForNull Object obj) {
            if (!(obj instanceof ConverterComposition)) {
                return false;
            }
            ConverterComposition converterComposition = (ConverterComposition) obj;
            if (!this.first.equals(converterComposition.first) || !this.second.equals(converterComposition.second)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (this.first.hashCode() * 31) + this.second.hashCode();
        }

        public String toString() {
            String valueOf = String.valueOf(this.first);
            String valueOf2 = String.valueOf(this.second);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 10 + String.valueOf(valueOf2).length());
            sb.append(valueOf);
            sb.append(".andThen(");
            sb.append(valueOf2);
            sb.append(")");
            return sb.toString();
        }
    }

    @CheckForNull
    @Deprecated
    public final B apply(@CheckForNull A a2) {
        return convert(a2);
    }

    public boolean equals(@CheckForNull Object obj) {
        return super.equals(obj);
    }

    public static <A, B> Converter<A, B> from(Function<? super A, ? extends B> function, Function<? super B, ? extends A> function2) {
        return new FunctionBasedConverter(function, function2);
    }

    private static final class FunctionBasedConverter<A, B> extends Converter<A, B> implements Serializable {
        private final Function<? super B, ? extends A> backwardFunction;
        private final Function<? super A, ? extends B> forwardFunction;

        private FunctionBasedConverter(Function<? super A, ? extends B> function, Function<? super B, ? extends A> function2) {
            this.forwardFunction = (Function) Preconditions.checkNotNull(function);
            this.backwardFunction = (Function) Preconditions.checkNotNull(function2);
        }

        /* access modifiers changed from: protected */
        public B doForward(A a2) {
            return this.forwardFunction.apply(a2);
        }

        /* access modifiers changed from: protected */
        public A doBackward(B b2) {
            return this.backwardFunction.apply(b2);
        }

        public boolean equals(@CheckForNull Object obj) {
            if (!(obj instanceof FunctionBasedConverter)) {
                return false;
            }
            FunctionBasedConverter functionBasedConverter = (FunctionBasedConverter) obj;
            if (!this.forwardFunction.equals(functionBasedConverter.forwardFunction) || !this.backwardFunction.equals(functionBasedConverter.backwardFunction)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return (this.forwardFunction.hashCode() * 31) + this.backwardFunction.hashCode();
        }

        public String toString() {
            String valueOf = String.valueOf(this.forwardFunction);
            String valueOf2 = String.valueOf(this.backwardFunction);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 18 + String.valueOf(valueOf2).length());
            sb.append("Converter.from(");
            sb.append(valueOf);
            sb.append(", ");
            sb.append(valueOf2);
            sb.append(")");
            return sb.toString();
        }
    }

    public static <T> Converter<T, T> identity() {
        return IdentityConverter.INSTANCE;
    }

    private static final class IdentityConverter<T> extends Converter<T, T> implements Serializable {
        static final IdentityConverter<?> INSTANCE = new IdentityConverter<>();
        private static final long serialVersionUID = 0;

        /* access modifiers changed from: protected */
        public T doBackward(T t) {
            return t;
        }

        /* access modifiers changed from: protected */
        public T doForward(T t) {
            return t;
        }

        public IdentityConverter<T> reverse() {
            return this;
        }

        private IdentityConverter() {
        }

        /* access modifiers changed from: package-private */
        public <S> Converter<T, S> doAndThen(Converter<T, S> converter) {
            return (Converter) Preconditions.checkNotNull(converter, "otherConverter");
        }

        public String toString() {
            return "Converter.identity()";
        }

        private Object readResolve() {
            return INSTANCE;
        }
    }
}
