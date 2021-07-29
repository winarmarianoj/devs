package edu.labIV.mapper;

import edu.labIV.dao.AccountDao;
import edu.labIV.entity.Account;

public class AccountMapper {

    private final AccountDao accountDao;

    public AccountMapper() {
        this.accountDao = new AccountDao();
    }

    /** Persiste una cuenta nueva en la base de datos.
     * @param account Cuanta a almacenar
     * @return - True Si la cuenta fue almacenada correctamente.
     *         - False Si falla al momento de guardar la cuenta.
     */
    public boolean save(Account account) {
        return accountDao.save(account);
    }

    /** Elimina una cuenta de la base de datos.
     * @param email e-mail de la cuenta a eliminar.
     * @return - True si la cuenta fue eliminada exitosamente
     *         - False en caso no haya ninguna cuanta vinculada con el email.
     */
    public boolean delete(String email){
        int id = accountDao.getIdFromEmail(email);
        return accountDao.delete(id);
    }

    public boolean delete(int id){
        return accountDao.delete(id);
    }

    /** Obtiene una cuenta.
     * @param email e-mail de la cuenta.
     * @return Cuenta obtenida de la base de datos.
     *         En caso que no haya cuentas vinculadas al email, devuelve null.
     */
    public Account get(String email){
        int id = accountDao.getIdFromEmail(email);
        return accountDao.get(id);
    }

    /** Obtiene una cuenta.
     * @param id id de la cuenta.
     * @return Cuenta obtenida de la base de datos.
     *         En caso que no haya cuentas vinculadas al email, devuelve null.
     */
    public Account get(int id){
        return accountDao.get(id);
    }

    /** Actualiza los datos de una cuenta.
     * @return - True si alguno de los datos se modifico correctamente.
     *         - False En caso que ningun dato haya sido modificado o que no se encuentren
     *         la cuenta no exista.
     */
    public boolean update(Account account){
        return accountDao.update(account);
    }


}
