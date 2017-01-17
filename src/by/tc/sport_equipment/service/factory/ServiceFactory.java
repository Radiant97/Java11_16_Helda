package by.tc.sport_equipment.service.factory;

import by.tc.sport_equipment.service.ClientService;
import by.tc.sport_equipment.service.ShopService;
import by.tc.sport_equipment.service.impl.ClientServiceImpl;
import by.tc.sport_equipment.service.impl.ShopServiceImpl;

public final class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final ClientService clientService = new ClientServiceImpl();
    private final ShopService shopService = new ShopServiceImpl();

    private ServiceFactory(){}

    public static ServiceFactory getInstance() { return instance; }

    public ClientService getClientService() { return clientService; }

    public ShopService getShopService() { return shopService; }
}
