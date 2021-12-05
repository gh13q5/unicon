<c:forEach var="Cgame" items="${categoryGameList}" varStatus="status">
	<a href="<c:url value='/reservation/game'>
            			<c:param name='game_id' value='${category.id}' />
            		</c:url>">
		<div class="card" style="width: 15rem;">
			<img src="images/wallR.jpg" class="card-img-top" alt="...">
			<div class="card-body">
				<h5 class="card-title"><c:out value="${Cgame.name}"></c:out></h5>
				<p class="card-text"><c:out value="${Cgame.description}"></c:out></p>
				<p class="card-text">
					<small class="text-muted">~<c:out value="${Cgame.end_date}"></c:out></small>
				</p>
			</div>
		</div>
	</a>
</c:forEach>