import com.yinuo.base.state.machine.Action;
import com.yinuo.base.state.machine.Condition;
import com.yinuo.base.state.machine.StateMachine;
import com.yinuo.base.state.machine.builder.StateMachineBuilder;
import com.yinuo.base.state.machine.builder.StateMachineBuilderFactory;
import com.yinuo.base.state.machine.impl.Debugger;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

/**
 * StateMachinePlantUMLTest
 *
 * @author Frank Zhang
 * @date 2020-02-09 7:53 PM
 */
public class StateMachinePlantUMLTest {

    @Before
    public void init() {
        Debugger.enableDebug();
    }

    @Test
    public void testPlantUML() {
        StateMachineBuilder<PriceAdjustmentTaskStatusEnum, PriceAdjustmentTaskEventEnum, StateMachineTest.Context> builder = StateMachineBuilderFactory.create();

        builder.externalTransition()
                .from(PriceAdjustmentTaskStatusEnum.None)
                .to(PriceAdjustmentTaskStatusEnum.Supplier_Processing)
                .on(PriceAdjustmentTaskEventEnum.Create)
                .when(checkCondition())
                .perform(doAction());

        // 商家调价
        Stream.of(PriceAdjustmentTaskStatusEnum.Supplier_Processing, PriceAdjustmentTaskStatusEnum.Supplier_Manager_Processing, PriceAdjustmentTaskStatusEnum.Price_Manager_Processing)
                .forEach(status ->
                        builder.externalTransition()
                                .from(status)
                                .to(PriceAdjustmentTaskStatusEnum.Closed)
                                .on(PriceAdjustmentTaskEventEnum.Supplier_Agree)
                                .when(checkCondition())
                                .perform(doAction())
                );

        // 商家 -上升至-> 控商小二
        builder.externalTransition()
                .from(PriceAdjustmentTaskStatusEnum.Supplier_Processing)
                .to(PriceAdjustmentTaskStatusEnum.Supplier_Manager_Processing)
                .on(PriceAdjustmentTaskEventEnum.Supplier_Reject)
                .when(checkCondition())
                .perform(doAction());

        builder.externalTransition()
                .from(PriceAdjustmentTaskStatusEnum.Supplier_Processing)
                .to(PriceAdjustmentTaskStatusEnum.Supplier_Manager_Processing)
                .on(PriceAdjustmentTaskEventEnum.Supplier_Timeout)
                .when(checkCondition())
                .perform(doAction());

        // 申请申请高于P0售卖
        builder.externalTransition()
                .from(PriceAdjustmentTaskStatusEnum.Supplier_Manager_Processing)
                .to(PriceAdjustmentTaskStatusEnum.Price_Manager_Processing)
                .on(PriceAdjustmentTaskEventEnum.Apply_Over_P0_Sell)
                .when(checkCondition())
                .perform(doAction());

        // 同意高于P0价售卖
        builder.externalTransition()
                .from(PriceAdjustmentTaskStatusEnum.Price_Manager_Processing)
                .to(PriceAdjustmentTaskStatusEnum.Closed)
                .on(PriceAdjustmentTaskEventEnum.Agree_Over_P0_Sell)
                .when(checkCondition())
                .perform(doAction());

        // 拒绝高于P0价售卖
        builder.externalTransition()
                .from(PriceAdjustmentTaskStatusEnum.Price_Manager_Processing)
                .to(PriceAdjustmentTaskStatusEnum.Supplier_Manager_Processing)
                .on(PriceAdjustmentTaskEventEnum.Reject_Over_P0_Sell)
                .when(checkCondition())
                .perform(doAction());

        // 普通字段更新事件
        Stream.of(PriceAdjustmentTaskStatusEnum.Supplier_Processing, PriceAdjustmentTaskStatusEnum.Supplier_Manager_Processing, PriceAdjustmentTaskStatusEnum.Price_Manager_Processing)
                .forEach(status -> builder
                        .internalTransition()
                        .within(status)
                        .on(PriceAdjustmentTaskEventEnum.Normal_Update)
                        .when(checkCondition())
                        .perform(doAction())
                );

        // P0价变更事件、页面价高于合理价事件
        Stream.of(PriceAdjustmentTaskEventEnum.P0_Changed, PriceAdjustmentTaskEventEnum.Page_Price_changed)
                .forEach(event -> builder.externalTransitions()
                        .fromAmong(PriceAdjustmentTaskStatusEnum.Supplier_Processing, PriceAdjustmentTaskStatusEnum.Supplier_Manager_Processing,
                                PriceAdjustmentTaskStatusEnum.Price_Manager_Processing)
                        .to(PriceAdjustmentTaskStatusEnum.Closed)
                        .on(event)
                        .when(checkCondition())
                        .perform(doAction()));

        StateMachine stateMachine = builder.build("AdjustPriceTask");
        String plantUML = stateMachine.generatePlantUML();
        System.out.println(plantUML);

    }

    private Condition<StateMachineTest.Context> checkCondition() {
        return (ctx) -> {
            return true;
        };
    }

    private Action<PriceAdjustmentTaskStatusEnum, PriceAdjustmentTaskEventEnum, StateMachineTest.Context> doAction() {
        return (from, to, event, ctx) -> {
            System.out.println(ctx.operator + " is operating " + ctx.entityId + " from:" + from + " to:" + to + " on:" + event);
        };
    }
}
