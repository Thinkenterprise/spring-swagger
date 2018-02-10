package com.thinkenterprise.controller.converter;

import java.io.IOException;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.thinkenterprise.domain.route.SimpleRoute;

public class KyroHttpMessageConverter extends AbstractHttpMessageConverter<Object> {

		public static final MediaType KRYO = new MediaType("application", "x-kryo");

		private static final ThreadLocal<Kryo> kryoThreadLocal = new ThreadLocal<Kryo>() {
			@Override
			protected Kryo initialValue() {
				final Kryo kryo = new Kryo();
				kryo.register(SimpleRoute.class);
				return kryo;
			}
		};

		public KyroHttpMessageConverter() {
			super(KRYO);
		}

		@Override
		protected boolean supports(final Class<?> clazz) {
			return Object.class.isAssignableFrom(clazz);
		}

		@Override
		protected Object readInternal(final Class<? extends Object> clazz,
				final HttpInputMessage inputMessage) throws IOException {
			final Input input = new Input(inputMessage.getBody());
			return kryoThreadLocal.get().readClassAndObject(input);
		}

		@Override
		protected void writeInternal(final Object object,
				final HttpOutputMessage outputMessage) throws IOException {
			final Output output = new Output(outputMessage.getBody());
			kryoThreadLocal.get().writeClassAndObject(output, object);
			output.flush();
		}

		@Override
		protected MediaType getDefaultContentType(final Object object) {
			return KRYO;
		}
		
}

