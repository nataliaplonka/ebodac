package org.motechproject.ebodac.repository;

import org.joda.time.DateTime;
import org.motechproject.commons.api.Range;
import org.motechproject.ebodac.domain.ReportPrimerVaccination;
import org.motechproject.mds.annotations.Lookup;
import org.motechproject.mds.annotations.LookupField;
import org.motechproject.mds.service.MotechDataService;

import java.util.List;

public interface ReportPrimerVaccinationDataService extends MotechDataService<ReportPrimerVaccination> {

    @Lookup
    List<ReportPrimerVaccination> findReportByDate(@LookupField(name = "date") Range<DateTime> dateRange);
}
