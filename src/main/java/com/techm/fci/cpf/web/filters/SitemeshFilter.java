package com.techm.fci.cpf.web.filters;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class SitemeshFilter extends ConfigurableSiteMeshFilter {
	@Override
	protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
		builder.addDecoratorPath("/*", "/template/decorator.jsp")
				.addDecoratorPath("/loadReportDashboard", "/template/internaldecorator.jsp")
				.addDecoratorPath("/dashboard/*", "/template/applicationDashboard.jsp")
				.addDecoratorPath("/eligibilityStudent", "/template/internaldecorator.jsp")
				.addDecoratorPath("/loadCustomActiviti", "/template/internaldecorator.jsp")
				.addDecoratorPath("/loadActiviti", "/template/internaldecorator.jsp")
				.addDecoratorPath("/studentList", "/template/internaldecorator.jsp")
				.addDecoratorPath("/loadHistory", "/template/internaldecorator.jsp")
				.addDecoratorPath("/myprofile", "/template/internaldecorator.jsp")
				.addDecoratorPath("/manageDocuments", "/template/internaldecorator.jsp")
				.addDecoratorPath("/assessmentDetailsCollege", "/template/internaldecorator.jsp")
				.addDecoratorPath("/circularStatus", "/template/internaldecorator.jsp")				
				.addDecoratorPath("/loadActivitiWithParameters", "/template/internaldecorator.jsp")
				.addDecoratorPath("/processActiviti", "/template/internaldecorator.jsp")
				.addDecoratorPath("/loadPaymentAdminSuccess", "/template/internaldecorator.jsp")
				.addDecoratorPath("/loadPaymentAdminFailure", "/template/internaldecorator.jsp")
				.addDecoratorPath("/loadAdmin", "/template/internaldecorator.jsp")
				.addDecoratorPath("/admin/*", "/template/admin/decorator.html")
				.addDecoratorPath("/aboutus/*", "/template/aboutusdecorator.jsp")
				.addDecoratorPath("/actnamendments/*", "/template/actnamendmentsdecorator.jsp")
				.addDecoratorPath("/informationdesk/*", "/template/informationdeskdecorator.jsp")
				.addDecoratorPath("/rulesnregulations/*", "/template/rulesnregulationsdecorator.jsp")
				.addDecoratorPath("/awards/*", "/template/awardsdecorator.jsp")
				.addDecoratorPath("/neet/*", "/template/neetdecorator.jsp")
				.addDecoratorPath("/mediaroom/*", "/template/mediadecorator.jsp")
				.addDecoratorPath("/meetings/*", "/template/meetingsdecorator.jsp")
				.addDecoratorPath("/popup/*", "/template/popupdecorator.jsp")
				.addDecoratorPath("/informationdesk/popup/*", "/template/popupdecorator.jsp")
				.addDecoratorPath("/meetings/popup/*", "/template/popupdecorator.jsp")
				.addDecoratorPath("/actnamendments/popup/*", "/template/popupdecorator.jsp")
				.addDecoratorPath("/rulesnregulations/popup/*", "/template/popupdecorator.jsp")
				.addDecoratorPath("/footer/popup/*", "/template/popupdecorator.jsp")
				.addDecoratorPath("/aboutus/popup/*", "/template/popupdecorator.jsp")
				.addDecoratorPath("/trainingHistory", "/template/internaldecorator.jsp")
				.addDecoratorPath("/legal/*", "/template/legaldecorator.jsp")
				.addDecoratorPath("/open/getNewPage", "/template/newpagedecorator.jsp")
				.addDecoratorPath("/facultyDeclaration","/template/internaldecorator.jsp")
				.addDecoratorPath("/facultyDeclarationDean","/template/internaldecorator.jsp")
				.addDecoratorPath("/deficitOrRejectedListForm","/template/internaldecorator.jsp")
				.addDecoratorPath("/showAllTeacherList","/template/internaldecorator.jsp")
				.addDecoratorPath("/addTeacherDetails","/template/internaldecorator.jsp")
				.addDecoratorPath("/getDetailEDeclarationApprovalView","/template/internaldecorator.jsp")
				.addDecoratorPath("/getDetailEDeclarationDeficitApprovalView","/template/internaldecorator.jsp")				
				.addDecoratorPath("/programMaster","/template/internaldecorator.jsp")
				.addDecoratorPath("/collegeCenterMap","/template/internaldecorator.jsp")
				.addDecoratorPath("/FDMCenterCollegeMapping","/template/internaldecorator.jsp")
				.addDecoratorPath("/FDMCenterCollegeTeacherMapping","/template/internaldecorator.jsp")
				.addDecoratorPath("/fdmCenterCollegeTeacherMappingUploadXel","/template/internaldecorator.jsp")
				.addDecoratorPath("/FDMresourceFaculty","/template/internaldecorator.jsp")
				.addDecoratorPath("/residentDeclaration","/template/internaldecorator.jsp")
				.addDecoratorPath("/addTeacherDetails","/template/internaldecorator.jsp")
				.addDecoratorPath("/legal/legalOpinionFileForPrint", "/template/newpagedecorator.jsp");
		
		
	}
}
