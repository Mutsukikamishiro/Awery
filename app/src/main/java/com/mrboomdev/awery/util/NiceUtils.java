package com.mrboomdev.awery.util;

import androidx.annotation.NonNull;

import com.mrboomdev.awery.sdk.util.Callbacks;

import org.jetbrains.annotations.Contract;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import java9.util.stream.Stream;
import java9.util.stream.StreamSupport;

/**
 * Just a collection of useful simple utility methods
 * @author MrBoomDev
 */
public class NiceUtils {

	/**
	 * @return The result of the callback if the param is not null
	 * @author MrBoomDev
	 */
	public static <A, B> A returnIfNotNull(B param, Callbacks.Result1<A, B> callback) {
		return param == null ? null : callback.run(param);
	}

	public static <A> void doIfNotNull(A param, Callbacks.Callback1<A> callback) {
		if(param != null) {
			callback.run(param);
		}
	}

	/**
	 * @return The first item in the collection that matches the query
	 * @author MrBoomDev
	 */
	public static <A> A findIn(@NonNull Callbacks.Result1<Boolean, A> checker, Collection<A> collection) {
		return stream(collection)
				.filter(checker::run)
				.findAny().orElse(null);
	}

	/**
	 * @return The result of the callback
	 * @author MrBoomDev
	 */
	public static <A, B> A returnWith(B object, @NonNull Callbacks.Result1<A, B> callback) {
		return callback.run(object);
	}

	/**
	 * @throws NullPointerException if object is null
	 * @return The object itself
	 * @author MrBoomDev
	 */
	@NonNull
	@Contract("null -> fail; !null -> param1")
	public static <T> T requireNonNull(T object) {
		if(object == null) throw new NullPointerException();
		return object;
	}

	/**
	 * @return The first object if it is not null, otherwise the second object
	 * @author MrBoomDev
	 */
	public static <T> T requireNonNullElse(T firstObject, T secondObject) {
		return firstObject != null ? firstObject : secondObject;
	}

	/**
	 * @return True if the object is not null
	 */
	public static boolean nonNull(Object obj) {
		return obj != null;
	}

	/**
	 * @return A stream from the collection compatible with old Androids
	 * @author MrBoomDev
	 */
	@NonNull
	@Contract("_ -> new")
	public static <E> Stream<E> stream(Collection<E> e) {
		return StreamSupport.stream(e);
	}

	/**
	 * @return A stream from the array compatible with old Androids
	 * @author MrBoomDev
	 */
	@SafeVarargs
	@NonNull
	@Contract("_ -> new")
	public static <E> Stream<E> stream(E... e) {
		return StreamSupport.stream(Arrays.asList(e));
	}

	/**
	 * @return A stream from map entries set compatible with old Androids
	 * @author MrBoomDev
	 */
	@NonNull
	@Contract("_ -> new")
	public static <K, V> Stream<Map.Entry<K,V>> stream(@NonNull Map<K, V> map) {
		return StreamSupport.stream(map.entrySet());
	}
}