<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<footer class="main-footer">
    <div class="footer-columns">
        
        <section class="footer-col">
            <h4>Chi Siamo</h4>
            <ul>
                <li><a href="${pageContext.request.contextPath}/pages/about.jsp">about us</a></li>
                <li><a href="${pageContext.request.contextPath}/pages/prodotti.jsp">i nostri prodotti</a></li>
            </ul>
        </section>

        <section class="footer-col">
            <h4>Spedizioni e Resi</h4>
            <ul>
                <li><a href="${pageContext.request.contextPath}/pages/spedizioni.jsp">costi di spedizione</a></li>
                <li><a href="${pageContext.request.contextPath}/pages/resi.jsp">resi</a></li>
                <li><a href="${pageContext.request.contextPath}/pages/termini.jsp">termini e condizioni</a></li>
            </ul>
        </section>

        <section class="footer-col">
            <h4>Contatti</h4>
            <div class="social-links">

                <a href="#" class="social-item">
                    <img src="${pageContext.request.contextPath}/img/facebook.png" 
                         alt="Facebook" class="social-icon">
                    <span>Facebook</span>
                </a>

                <a href="#" class="social-item">
                    <img src="${pageContext.request.contextPath}/img/instagram.png" 
                         alt="Instagram" class="social-icon">
                    <span>Instagram</span>
                </a>

                <a href="#" class="social-item">
                    <img src="${pageContext.request.contextPath}/img/email.png" 
                         alt="Email" class="social-icon">
                    <span>Email</span>
                </a>

            </div>
        </section>

        <section class="footer-col">
            <h4>Help Desk</h4>
            <ul>
                <li><a href="${pageContext.request.contextPath}/pages/supporto.jsp">supporto</a></li>
                <li><a href="${pageContext.request.contextPath}/pages/faq.jsp">domande frequenti</a></li>
                <li><a href="${pageContext.request.contextPath}/pages/contatti.jsp">contact us</a></li>
            </ul>
        </section>

    </div>

    <div class="footer-bottom">
        <p>&copy; 2026 Sari Sari Shop. Tutti i diritti riservati.</p>
    </div>
</footer>
