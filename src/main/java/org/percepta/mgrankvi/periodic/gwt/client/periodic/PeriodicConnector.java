package org.percepta.mgrankvi.periodic.gwt.client.periodic;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.ComponentConnector;
import com.vaadin.client.ConnectorHierarchyChangeEvent;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractHasComponentsConnector;
import com.vaadin.client.ui.SimpleManagedLayout;
import com.vaadin.shared.ui.Connect;
import org.percepta.mgrankvi.periodic.Periodic;

import java.util.List;

@Connect(Periodic.class)
public class PeriodicConnector extends AbstractHasComponentsConnector implements SimpleManagedLayout {

    private static final long serialVersionUID = 2341597615886480418L;

    @Override
    public void init() {
        super.init();
    }

    @Override
    protected Widget createWidget() {
        return GWT.create(CPeriodic.class);
    }

    @Override
    public CPeriodic getWidget() {
        return (CPeriodic) super.getWidget();
    }

    @Override
    public PeriodicState getState() {
        return (PeriodicState) super.getState();
    }

    @Override
    public void onStateChanged(final StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);
        if (stateChangeEvent.hasPropertyChanged("width") || stateChangeEvent.hasPropertyChanged("height")) {
            getWidget().setSize(getState().widthPx, getState().heightPx);
        }
        if(stateChangeEvent.hasPropertyChanged("immediate")){
            getWidget().setImmediate(getState().immediate);
        }
        if(stateChangeEvent.hasPropertyChanged("scale")){
            getWidget().setScale(getState().scale);
        }
    }

    @Override
    public void updateCaption(ComponentConnector connector) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onConnectorHierarchyChange(ConnectorHierarchyChangeEvent connectorHierarchyChangeEvent) {
        final List<ComponentConnector> children = getChildComponents();
        final CPeriodic widget = getWidget();
        widget.clearChildItems();
        for (final ComponentConnector connector : children) {
            widget.add(connector.getWidget());
        }
        for (final ComponentConnector child : connectorHierarchyChangeEvent.getOldChildren()) {
            child.getWidget().removeFromParent();
        }
    }

    @Override
    public void layout() {
        getWidget().clearCanvas();
        getWidget().paint();
    }
}