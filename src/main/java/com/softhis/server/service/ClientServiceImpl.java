package com.softhis.server.service;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.softhis.server.dao.ClientDao;
import com.softhis.server.model.Client;
import com.softhis.server.model.Order;
import com.softhis.shared.dto.ReportLineDto;
import com.softhis.shared.dto.ReportOrderDto;
import com.softhis.shared.service.ClientService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service("clientService")
@Transactional
public class ClientServiceImpl extends RemoteServiceServlet implements ClientService {
    private static final Logger LOGGER = LogManager.getLogger(ClientServiceImpl.class.getSimpleName());

    @Autowired
    private ClientDao clientDao;

    public List<ReportLineDto> getClientReport(int numberOfExpensiveListed) throws IllegalArgumentException {
        LOGGER.info("ClientServiceImpl.getClientReport()");

        List<Client> clients = null;
        try {
            clients = clientDao.getClients();
        } catch (Exception e) {
            LOGGER.error(e);
        }

        List<ReportLineDto> result = new ArrayList<>();

        for (Client client : clients) {
            ReportLineDto reportLine = new ReportLineDto();

            reportLine.setFirstName(client.getFirstName());
            reportLine.setLastName(client.getLastName());

            int amount = 0;
            List<ReportOrderDto> reportOrders = new ArrayList<>();
            for (Order order : client.getOrders()) {
                amount += order.getOrderAmount();
                reportOrders.add(new ReportOrderDto(order.getOrderDate(), order.getOrderAmount()));
            }
            reportLine.setAmount(amount);

            Collections.sort(reportOrders, new Comparator<ReportOrderDto>() {
                @Override
                public int compare(ReportOrderDto order1, ReportOrderDto order2) {
                    if (order1.getAmount() > order2.getAmount()) {
                        return 1;
                    } else if (order1.getAmount() < order2.getAmount()) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            });
            reportLine.setMostExpensiveOrders(reportOrders.subList(0, numberOfExpensiveListed));

            result.add(reportLine);
        }

        return result;
    }
}
