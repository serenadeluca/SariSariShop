<div class="pagination">
    <%-- Bottone Indietro --%>
    <a href="?page=${currentPage - 1}">Indietro</a>

    <%-- Ciclo per generare i numeri di pagina --%>
    <c:forEach begin="1" end="${totalPages}" var="i">
        <a href="?page=${i}" class="${currentPage == i ? 'active' : ''}">${i}</a>
    </c:forEach>

    <%-- Bottone Avanti --%>
    <a href="?page=${currentPage + 1}">Avanti</a>
</div>