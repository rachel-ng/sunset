package retrofit2;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;
import javax.annotation.Nullable;
import okhttp3.ResponseBody;
import retrofit2.Converter;

final class OptionalConverterFactory extends Converter.Factory {
    static final Converter.Factory INSTANCE = new OptionalConverterFactory();

    OptionalConverterFactory() {
    }

    @Nullable
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotationArr, Retrofit retrofit) {
        if (getRawType(type) != Platform$$ExternalSyntheticApiModelOutline0.m$1()) {
            return null;
        }
        return new OptionalConverter(retrofit.responseBodyConverter(getParameterUpperBound(0, (ParameterizedType) type), annotationArr));
    }

    static final class OptionalConverter<T> implements Converter<ResponseBody, Optional<T>> {
        final Converter<ResponseBody, T> delegate;

        OptionalConverter(Converter<ResponseBody, T> converter) {
            this.delegate = converter;
        }

        public Optional<T> convert(ResponseBody responseBody) throws IOException {
            return Platform$$ExternalSyntheticApiModelOutline0.m((Object) this.delegate.convert(responseBody));
        }
    }
}
