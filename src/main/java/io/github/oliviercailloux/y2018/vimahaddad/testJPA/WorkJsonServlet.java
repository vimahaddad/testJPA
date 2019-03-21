package io.github.oliviercailloux.y2018.vimahaddad.testJPA;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;


@SuppressWarnings("serial")
@WebServlet("work")
public class WorkJsonServlet extends HttpServlet {
	private static final Logger LOGGER = Logger.getLogger(WorkJsonServlet.class.getCanonicalName());
	@PersistenceContext
	private EntityManager em;

	@Inject
	private WorkService workService;// = new CommentService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
		resp.setContentType(MediaType.APPLICATION_JSON);
		resp.setLocale(Locale.ENGLISH);
		/**
		 * Converting to Json format
		 */
		try (Jsonb jsonb = JsonbBuilder.create();) {

			/**
			 * Jpa will be implemented in the next sprint
			 */

			final List<Work> allItems = workService.getAll();
			String jsonItem = "";

			for (Work work : allItems) {

				// Work item = this.initWork(req);

				jsonItem = jsonItem + jsonb.toJson(work) + "\n";
			}

			resp.setStatus(HttpServletResponse.SC_OK);
			resp.getWriter().println(jsonItem);
			LOGGER.info(" Display object in JSON format " + jsonItem);

		} catch (Exception e) {
			// Send error status
			LOGGER.warning(" Error  " + e.toString());
			throw new ServletException(e);

		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding(StandardCharsets.UTF_8.name());
		resp.setContentType(MediaType.TEXT_PLAIN);
		resp.setLocale(Locale.ENGLISH);
		try (Jsonb jsonb = JsonbBuilder.create();) {
			try (BufferedReader reader = req.getReader()) {

				Work work = jsonb.fromJson(reader, Work.class);
				workService.persist(work);
			}
			resp.setStatus(HttpServletResponse.SC_OK);
			resp.getWriter().println(
					"The object is successfully initialized. Database insertion  will be implemented in the next sprint");
		}

		catch (Exception e) {

			LOGGER.warning(" Error  " + e.toString());
			resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "insert failed.");
		}

	}

	public Work initWork(HttpServletRequest req) throws NumberFormatException, NullPointerException {

		String contextForTheWork = req.getParameter("contextForTheWork");
		int idWork = Integer.parseInt(req.getParameter("idWork"));
		/*		String titleOfWork = req.getParameter("titleOfWork");
		String formOfWork = req.getParameter("formOfWork");
		Collection<String> collectionTitleOfWork = new ArrayList<>();

		if (!StringUtils.isBlank(titleOfWork)) {

			collectionTitleOfWork.add(titleOfWork);
		}*/

		Work work = new Work(idWork, contextForTheWork);

		return work;

	}

}
