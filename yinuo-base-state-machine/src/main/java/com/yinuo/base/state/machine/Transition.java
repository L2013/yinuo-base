package com.yinuo.base.state.machine;

import com.yinuo.base.state.machine.impl.TransitionType;

/**
 * {@code Transition} is something what a state machine associates with a state
 * changes.
 *
 * @author Frank Zhang
 *
 * @param <S> the type of state
 * @param <E> the type of event
 * @param <C> the type of user defined context, which is used to hold application data
 *
 * @date 2020-02-07 2:20 PM
 */
public interface Transition<S, E, C>{
    /**
     * Gets the source state of this transition.
     *
     * @return the source state
     */
    com.yinuo.base.state.machine.State<S,E,C> getSource();

    void setSource(com.yinuo.base.state.machine.State<S, E, C> state);

    E getEvent();

    void setEvent(E event);

    void setType(TransitionType type);
    /**
     * Gets the target state of this transition.
     *
     * @return the target state
     */
    com.yinuo.base.state.machine.State<S,E,C> getTarget();

    void setTarget(com.yinuo.base.state.machine.State<S, E, C> state);

    /**
     * Gets the guard of this transition.
     *
     * @return the guard
     */
    com.yinuo.base.state.machine.Condition<C> getCondition();

    void setCondition(com.yinuo.base.state.machine.Condition<C> condition);

    com.yinuo.base.state.machine.Action<S,E,C> getAction();

    void setAction(com.yinuo.base.state.machine.Action<S, E, C> action);

    /**
     * Do transition from source state to target state.
     *
     * @return the target state
     */

    com.yinuo.base.state.machine.State<S,E,C> transit(C ctx);
    /**
     * Verify transition correctness
     */
    void verify();
}
