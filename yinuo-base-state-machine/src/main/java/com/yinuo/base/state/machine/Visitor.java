package com.yinuo.base.state.machine;

/**
 * Visitor
 *
 * @author Frank Zhang
 * @date 2020-02-08 8:41 PM
 */
public interface Visitor {

    char LF = '\n';

    /**
     * @param visitable the element to be visited.
     * @return
     */
    String visitOnEntry(com.yinuo.base.state.machine.StateMachine<?, ?, ?> visitable);

    /**
     * @param visitable the element to be visited.
     * @return
     */
    String visitOnExit(com.yinuo.base.state.machine.StateMachine<?, ?, ?> visitable);

    /**
     * @param visitable the element to be visited.
     * @return
     */
    String visitOnEntry(com.yinuo.base.state.machine.State<?, ?, ?> visitable);

    /**
     * @param visitable the element to be visited.
     * @return
     */
    String visitOnExit(com.yinuo.base.state.machine.State<?, ?, ?> visitable);
}
