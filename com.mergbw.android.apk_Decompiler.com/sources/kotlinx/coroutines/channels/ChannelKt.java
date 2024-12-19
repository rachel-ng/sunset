package kotlinx.coroutines.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.channels.ChannelResult;

@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\f\u001a\u001e\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\u0007\u001a>\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\t\u0018\u00010\b\u001aX\u0010\n\u001a\u0002H\u000b\"\u0004\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\f2#\u0010\r\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u0002H\u000b0\bH\bø\u0001\u0000ø\u0001\u0001\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001a^\u0010\u0014\u001a\b\u0012\u0004\u0012\u0002H\u000b0\f\"\u0004\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\f2#\u0010\u0015\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\t0\bH\bø\u0001\u0000ø\u0001\u0001\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0004\b\u0016\u0010\u0013\u001a^\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000b0\f\"\u0004\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\f2#\u0010\u0015\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u00020\t0\bH\bø\u0001\u0000ø\u0001\u0001\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0004\b\u0017\u0010\u0013\u001a\\\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u000b0\f\"\u0004\b\u0000\u0010\u000b*\b\u0012\u0004\u0012\u0002H\u000b0\f2!\u0010\u0015\u001a\u001d\u0012\u0013\u0012\u0011H\u000b¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0019\u0012\u0004\u0012\u00020\t0\bH\bø\u0001\u0000ø\u0001\u0001\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0000¢\u0006\u0004\b\u001a\u0010\u0013\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u001b"}, d2 = {"Channel", "Lkotlinx/coroutines/channels/Channel;", "E", "capacity", "", "onBufferOverflow", "Lkotlinx/coroutines/channels/BufferOverflow;", "onUndeliveredElement", "Lkotlin/Function1;", "", "getOrElse", "T", "Lkotlinx/coroutines/channels/ChannelResult;", "onFailure", "", "Lkotlin/ParameterName;", "name", "exception", "getOrElse-WpGqRn0", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "onClosed", "action", "onClosed-WpGqRn0", "onFailure-WpGqRn0", "onSuccess", "value", "onSuccess-WpGqRn0", "kotlinx-coroutines-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Channel.kt */
public final class ChannelKt {
    /* renamed from: getOrElse-WpGqRn0  reason: not valid java name */
    public static final <T> T m341getOrElseWpGqRn0(Object obj, Function1<? super Throwable, ? extends T> function1) {
        return obj instanceof ChannelResult.Failed ? function1.invoke(ChannelResult.m349exceptionOrNullimpl(obj)) : obj;
    }

    /* renamed from: onSuccess-WpGqRn0  reason: not valid java name */
    public static final <T> Object m344onSuccessWpGqRn0(Object obj, Function1<? super T, Unit> function1) {
        if (!(obj instanceof ChannelResult.Failed)) {
            function1.invoke(obj);
        }
        return obj;
    }

    /* renamed from: onFailure-WpGqRn0  reason: not valid java name */
    public static final <T> Object m343onFailureWpGqRn0(Object obj, Function1<? super Throwable, Unit> function1) {
        if (obj instanceof ChannelResult.Failed) {
            function1.invoke(ChannelResult.m349exceptionOrNullimpl(obj));
        }
        return obj;
    }

    /* renamed from: onClosed-WpGqRn0  reason: not valid java name */
    public static final <T> Object m342onClosedWpGqRn0(Object obj, Function1<? super Throwable, Unit> function1) {
        if (obj instanceof ChannelResult.Closed) {
            function1.invoke(ChannelResult.m349exceptionOrNullimpl(obj));
        }
        return obj;
    }

    public static /* synthetic */ Channel Channel$default(int i, BufferOverflow bufferOverflow, Function1 function1, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        if ((i2 & 2) != 0) {
            bufferOverflow = BufferOverflow.SUSPEND;
        }
        if ((i2 & 4) != 0) {
            function1 = null;
        }
        return Channel(i, bufferOverflow, function1);
    }

    public static final <E> Channel<E> Channel(int i, BufferOverflow bufferOverflow, Function1<? super E, Unit> function1) {
        BufferedChannel bufferedChannel;
        BufferedChannel bufferedChannel2;
        BufferedChannel bufferedChannel3;
        if (i == -2) {
            if (bufferOverflow == BufferOverflow.SUSPEND) {
                bufferedChannel = new BufferedChannel(Channel.Factory.getCHANNEL_DEFAULT_CAPACITY$kotlinx_coroutines_core(), function1);
            } else {
                bufferedChannel = new ConflatedBufferedChannel(1, bufferOverflow, function1);
            }
            return bufferedChannel;
        } else if (i != -1) {
            if (i == 0) {
                if (bufferOverflow == BufferOverflow.SUSPEND) {
                    bufferedChannel2 = new BufferedChannel(0, function1);
                } else {
                    bufferedChannel2 = new ConflatedBufferedChannel(1, bufferOverflow, function1);
                }
                return bufferedChannel2;
            } else if (i == Integer.MAX_VALUE) {
                return new BufferedChannel<>(Integer.MAX_VALUE, function1);
            } else {
                if (bufferOverflow == BufferOverflow.SUSPEND) {
                    bufferedChannel3 = new BufferedChannel(i, function1);
                } else {
                    bufferedChannel3 = new ConflatedBufferedChannel(i, bufferOverflow, function1);
                }
                return bufferedChannel3;
            }
        } else if (bufferOverflow == BufferOverflow.SUSPEND) {
            return new ConflatedBufferedChannel<>(1, BufferOverflow.DROP_OLDEST, function1);
        } else {
            throw new IllegalArgumentException("CONFLATED capacity cannot be used with non-default onBufferOverflow".toString());
        }
    }

    public static /* synthetic */ Channel Channel$default(int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return Channel$default(i, (BufferOverflow) null, (Function1) null, 6, (Object) null);
    }
}
