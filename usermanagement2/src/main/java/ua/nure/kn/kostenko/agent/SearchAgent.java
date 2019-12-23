package ua.nure.kn.kostenko.agent;



import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;
import ua.nure.kn.kostenko.agent.behaviour.RequestServer;
import ua.nure.kn.kostenko.agent.behaviour.SearchRequestBehaviour;
import ua.nure.kn.kostenko.db.DaoFactory;
import ua.nure.kn.kostenko.db.DatabaseException;
import ua.nure.kn.kostenko.domain.User;

import java.util.Collection;

public class SearchAgent extends Agent {

    private AID[] aids = new AID[0];//added AID

    private SearchGui gui = null;

    @Override
    protected void setup() {
        super.setup();
        gui = new SearchGui(this);
        gui.setVisible(true);
        addBehaviour(new TickerBehaviour(this, 60000) {
            @Override
            protected void onTick() {
                DFAgentDescription description = new DFAgentDescription();
                description.setName(getAID());
                ServiceDescription serviceDescription = new ServiceDescription();
                serviceDescription.setName("JADE-Searching");
                serviceDescription.setType("searching");
                description.addServices(serviceDescription);
                try {
                    DFAgentDescription[] descriptions = DFService.search(myAgent, description);
                    AID[] newAids = new AID[descriptions.length];
                    for (int i = 0; i < newAids.length; i++) {
                        newAids[i] = descriptions[i].getName();
                    }
                    aids = newAids;
                } catch (FIPAException e) {
                    e.printStackTrace();
                }
            }
        });
        addBehaviour(new RequestServer());//added Behaiour
        DFAgentDescription description = new DFAgentDescription();// added Yellow pages  behaviour
        description.setName(getAID());
        ServiceDescription serviceDescription = new ServiceDescription();
        serviceDescription.setName("JADE-Searching");
        serviceDescription.setType("searching");
        description.addServices(serviceDescription);
        try {
            DFService.register(this, description);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        System.out.println("Agent " + getAID().getName() + " is ready! Awaiting orders!");
    }

    @Override
    protected void takeDown() {
        super.takeDown();
        try {
            DFService.deregister(this);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
        gui.setVisible(false);
        gui.dispose();
        System.out.println("Agent " + getAID().getName() + " has finished working!");
    }

    public void search(String firstName, String lastName) throws SearchException, DatabaseException {
        Collection<User> users = DaoFactory.getInstance().getUserDao().find(firstName, lastName);
        if (users.size() > 0) {
            showUsers(users);
        } else {
            addBehaviour(new SearchRequestBehaviour(firstName, lastName, aids));
        }
    }

    public void showUsers(Collection<User> users) {
        gui.addUsers(users);
    }


}
