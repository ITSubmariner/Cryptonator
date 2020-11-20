package org.pet.cryptonator.mapping;

public interface GenericMapper<S, T> {

    T mapSourceToDestination(S source);

    S mapDestinationToSource(T source);

}
