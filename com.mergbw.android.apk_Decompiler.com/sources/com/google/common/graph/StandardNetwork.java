package com.google.common.graph;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

@ElementTypesAreNonnullByDefault
class StandardNetwork<N, E> extends AbstractNetwork<N, E> {
    private final boolean allowsParallelEdges;
    private final boolean allowsSelfLoops;
    private final ElementOrder<E> edgeOrder;
    final MapIteratorCache<E, N> edgeToReferenceNode;
    private final boolean isDirected;
    final MapIteratorCache<N, NetworkConnections<N, E>> nodeConnections;
    private final ElementOrder<N> nodeOrder;

    StandardNetwork(NetworkBuilder<? super N, ? super E> networkBuilder) {
        this(networkBuilder, networkBuilder.nodeOrder.createMap(((Integer) networkBuilder.expectedNodeCount.or(10)).intValue()), networkBuilder.edgeOrder.createMap(networkBuilder.expectedEdgeCount.or(20).intValue()));
    }

    StandardNetwork(NetworkBuilder<? super N, ? super E> networkBuilder, Map<N, NetworkConnections<N, E>> map, Map<E, N> map2) {
        MapIteratorCache<N, NetworkConnections<N, E>> mapIteratorCache;
        this.isDirected = networkBuilder.directed;
        this.allowsParallelEdges = networkBuilder.allowsParallelEdges;
        this.allowsSelfLoops = networkBuilder.allowsSelfLoops;
        this.nodeOrder = networkBuilder.nodeOrder.cast();
        this.edgeOrder = networkBuilder.edgeOrder.cast();
        if (map instanceof TreeMap) {
            mapIteratorCache = new MapRetrievalCache<>(map);
        } else {
            mapIteratorCache = new MapIteratorCache<>(map);
        }
        this.nodeConnections = mapIteratorCache;
        this.edgeToReferenceNode = new MapIteratorCache<>(map2);
    }

    public Set<N> nodes() {
        return this.nodeConnections.unmodifiableKeySet();
    }

    public Set<E> edges() {
        return this.edgeToReferenceNode.unmodifiableKeySet();
    }

    public boolean isDirected() {
        return this.isDirected;
    }

    public boolean allowsParallelEdges() {
        return this.allowsParallelEdges;
    }

    public boolean allowsSelfLoops() {
        return this.allowsSelfLoops;
    }

    public ElementOrder<N> nodeOrder() {
        return this.nodeOrder;
    }

    public ElementOrder<E> edgeOrder() {
        return this.edgeOrder;
    }

    public Set<E> incidentEdges(N n) {
        return checkedConnections(n).incidentEdges();
    }

    public EndpointPair<N> incidentNodes(E e) {
        Object checkedReferenceNode = checkedReferenceNode(e);
        return EndpointPair.of((Network<?, ?>) this, checkedReferenceNode, ((NetworkConnections) Objects.requireNonNull(this.nodeConnections.get(checkedReferenceNode))).adjacentNode(e));
    }

    public Set<N> adjacentNodes(N n) {
        return checkedConnections(n).adjacentNodes();
    }

    public Set<E> edgesConnecting(N n, N n2) {
        NetworkConnections checkedConnections = checkedConnections(n);
        if (!this.allowsSelfLoops && n == n2) {
            return ImmutableSet.of();
        }
        Preconditions.checkArgument(containsNode(n2), "Node %s is not an element of this graph.", (Object) n2);
        return checkedConnections.edgesConnecting(n2);
    }

    public Set<E> inEdges(N n) {
        return checkedConnections(n).inEdges();
    }

    public Set<E> outEdges(N n) {
        return checkedConnections(n).outEdges();
    }

    public Set<N> predecessors(N n) {
        return checkedConnections(n).predecessors();
    }

    public Set<N> successors(N n) {
        return checkedConnections(n).successors();
    }

    /* access modifiers changed from: package-private */
    public final NetworkConnections<N, E> checkedConnections(N n) {
        NetworkConnections<N, E> networkConnections = this.nodeConnections.get(n);
        if (networkConnections != null) {
            return networkConnections;
        }
        Preconditions.checkNotNull(n);
        throw new IllegalArgumentException(String.format("Node %s is not an element of this graph.", new Object[]{n}));
    }

    /* access modifiers changed from: package-private */
    public final N checkedReferenceNode(E e) {
        N n = this.edgeToReferenceNode.get(e);
        if (n != null) {
            return n;
        }
        Preconditions.checkNotNull(e);
        throw new IllegalArgumentException(String.format("Edge %s is not an element of this graph.", new Object[]{e}));
    }

    /* access modifiers changed from: package-private */
    public final boolean containsNode(N n) {
        return this.nodeConnections.containsKey(n);
    }

    /* access modifiers changed from: package-private */
    public final boolean containsEdge(E e) {
        return this.edgeToReferenceNode.containsKey(e);
    }
}
