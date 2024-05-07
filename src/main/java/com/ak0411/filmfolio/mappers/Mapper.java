package com.ak0411.filmfolio.mappers;

import java.util.Collection;
import java.util.List;

public interface Mapper <A, B> {

    B mapTo(A a);

    A mapFrom(B b);

    List<B> mapAll(Collection<A> a);
}
