package com.softhis.shared.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.softhis.shared.dto.ReportLineDto;

import java.util.List;

@RemoteServiceRelativePath("report")
public interface ClientService extends RemoteService {
    List<ReportLineDto> getClientReport(int numberOfExpensiveListed) throws IllegalArgumentException;
}
