package plugin.pluginsamplere;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.World;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

public final class Main extends JavaPlugin implements Listener {

    private int count;
    public BigInteger val = new BigInteger("1"); // BigInteger型の val を定義

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        
    }

        /**
         * プレイヤーがスニークを開始/終了する際に起動されるイベントハンドラ。
         *
         * @param e イベント
         */
    @EventHandler
    public void onPlayerToggleSneak(PlayerToggleSneakEvent e) throws IOException {

        // BigIntegerを使用して、現在のカウントを素数判定
//        BigInteger val = BigInteger.valueOf(count);

//        if (val.isProbablePrime(10)) { // 素数判定を行う（引数は確度）
        // イベント発生時のプレイヤーやワールドなどの情報を変数に持つ。
        Player p = e.getPlayer();
        World world = p.getWorld();

        List<Color> colorList = List.of(Color.RED, Color.BLUE, Color.WHITE, Color.BLACK);
            if(count % 2 == 0){
                for(Color color:colorList) {
                    // 花火オブジェクトをプレイヤーのロケーション地点に対して出現させる。
                    Firework firework = world.spawn(p.getLocation(), Firework.class);
                    // 花火オブジェクトが持つメタ情報を取得。
                    FireworkMeta fireworkMeta = firework.getFireworkMeta();
                    // メタ情報に対して設定を追加したり、値の上書きを行う。
                    // 今回は青色で星型の花火を打ち上げる。
                    fireworkMeta.addEffect(
                            FireworkEffect.builder()
                                    .withColor(color.RED)
                                    .withColor(color.BLACK)
                                    .with(Type.BALL_LARGE)
                                    .withFlicker()
                                    .build());
                    fireworkMeta.setPower(1 + 1);

                    // 追加した情報で再設定する。
                    firework.setFireworkMeta(fireworkMeta);
                    System.out.println("スニークした回数が素数です: " + count);
//            System.out.println("次の素数は: " + val.nextProbablePrime());
                }
                Path path = Path.of("fireWork.tet");
                Files.writeString(path, "たーまやー");
                p.sendMessage(Files.readString(path));
            }
        count++;
    }


//    @EventHandler
//    public void onPlayerBed(PlayerBedEnterEvent e){
//        Player player = e.getPlayer();
//        ItemStack[] itemStacks = player.getInventory().getContents();

        // アイテムがnullでなく、最大スタックサイズが64で、現在のアイテム数が64未満の場合
        // そのアイテムをインベントリから削除
//        Arrays.stream(itemStacks).
//                filter(item -> !Objects.isNull(item)  && item.getMaxStackSize() == 64 && item.getAmount() < 64)
//                .forEach(item -> item.setAmount(0));

//        player.getInventory().setContents(itemStacks);
//    }

}
